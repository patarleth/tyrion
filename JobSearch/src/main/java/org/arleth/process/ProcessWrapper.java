package org.arleth.process;
import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author arleth
 */
public class ProcessWrapper {

    public final static ExecutorService executor = Executors.newCachedThreadPool();

    static {
        Runnable r = new Runnable() {

            @Override
            public void run() {
                ProcessWrapper.executor.shutdownNow();
            }
        };
        Thread t = new Thread(r);
        Runtime.getRuntime().addShutdownHook(t);
    }
    private Process process;
    private IOWrapper err;
    private IOWrapper out;
    private StringBuffer all = new StringBuffer();

    public ProcessWrapper(Process process) {
        this.process = process;
        err = new IOWrapper(all, process.getErrorStream(), false);
        out = new IOWrapper(all, process.getInputStream(), true);
        executor.submit(err);
        executor.submit(out);
    }

    public List<String> stdOutList() {
        return out.getList();
    }

    public String stderr() {
        return err.toString();
    }

    public String stdout() {
        return out.toString();
    }

    public String stdouterr() {
        return all.toString();
    }

    public void closeIOWrappers() {
        out.close();
        err.close();
    }

    public static String searchSystemPathForExe(String exeName) {
        String result = null;
        File currentDir = new File(exeName);
        if (currentDir.exists()) {
            result = currentDir.getAbsolutePath();
        } else {
            String path = System.getenv("path");
            if (path == null) {
                path = System.getenv("PATH");
            }
            if (path != null) {
                String[] parts = path.split("[;:]");
                for (String part : parts) {
                    File dir = new File(part);
                    if (dir.exists() && dir.isDirectory()) {
                        File exeFile = new File(dir, exeName);
                        if (exeFile.exists()) {
                            result = exeFile.getAbsolutePath();
                            break;
                        }
                    }
                }
            }
        }
        return result;
    }
}