package org.arleth.process;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author arleth
 */
public class PhantomJsProcessWrapper {

    private static final Logger logger = LoggerFactory.getLogger(PhantomJsProcessWrapper.class);
    private ExecutorService executor = null;
    private int timeout = 0;
    private String phantomJs;

    public static void main(String[] args) throws IOException {
        PhantomJsProcessWrapper phantomjs = new PhantomJsProcessWrapper();
        ProcessWrapper pw = phantomjs.execBlocked(args);
        logger.info(pw.stdouterr());
    }

    public PhantomJsProcessWrapper() {
        this(new File(System.getProperty("vario.phantomjs.file.location", ProcessWrapper.searchSystemPathForExe("phantomjs"))));
    }

    public PhantomJsProcessWrapper(ExecutorService executor, int timeout) {
        this(executor, timeout, new File(System.getProperty("vario.phantomjs.file.location", ProcessWrapper.searchSystemPathForExe("phantomjs"))));
    }

    public PhantomJsProcessWrapper(File phantomJs) {
        this(null, 0, phantomJs);
    }

    public PhantomJsProcessWrapper(ExecutorService executor, int processTimeout, File phantomJs) {
        /*
        System.out.println( "\nPhantomJsProcessWrapper " + phantomJs.getAbsolutePath()+"\n"+
        System.getProperty("vario.phantomjs.file.location") +"\n"+
        System.getProperty("vario.phantomjs.file.location", ProcessWrapper.searchSystemPathForExe("phantomjs"))+"\n");
         */
        this.executor = executor;
        //set default timeout to crawl site to 1 min if miss set
        if (executor != null && processTimeout <= 0) {
            this.timeout = 60;
        } else {
            this.timeout = processTimeout;
        }
        if (phantomJs.exists()) {
            this.phantomJs = phantomJs.getAbsolutePath();
        } else {
            throw new ExceptionInInitializerError("unknown phantomjs executable " + phantomJs.getAbsolutePath());
        }
    }

    private class ProcessWrapperCallable implements Callable<ProcessWrapper> {

        private String[] args;
        Process process = null;

        public ProcessWrapperCallable(String[] a) {
            this.args = a;
        }

        public void kill() {
            if (process != null) {
                logger.info("killing process " + process.toString());
                process.destroy();
                logger.info("process.exitValue() " + process.exitValue());
            }
        }

        @Override
        public ProcessWrapper call() {
            ProcessWrapper pw = null;
            try {
                ArrayList<String> execArgList = new ArrayList<String>();
                execArgList.add(phantomJs);
                if (args != null) {
                    for (int i = 0; i < args.length; i++) {
                        String arg = args[i];
                        if (arg != null) {
                            execArgList.add(arg);
                        }
                    }
                }
                String[] execArgs = execArgList.toArray(new String[execArgList.size()]);
                if (execArgs.length > 1) {
                    process = Runtime.getRuntime().exec(execArgs);
                    pw = new ProcessWrapper(process);
                    boolean done = false;
                    while (!done) {
                        try {
                            process.waitFor();
                            done = true;
                        } catch (InterruptedException ex) {
                            logger.error("interrupted while waiting for " + execArgs[0] + " process to exit", ex);
                        }
                    }
                }
            } catch (Exception e) {
                logger.error("boo", e);
            }

            return pw;
        }
    }

    public ProcessWrapper execBlocked(String... args) throws IOException {
        ProcessWrapper pw = null;
        ProcessWrapperCallable callable = new ProcessWrapperCallable(args);
        if (executor != null) {
            Future<ProcessWrapper> future = executor.submit(callable);
            try {
                pw = future.get(timeout, TimeUnit.SECONDS);
            } catch (InterruptedException ex) {
                logger.error("who interrupted me!?!?", null, ex);
            } catch (ExecutionException ex) {
                logger.error("the ", null, ex);
            } catch (TimeoutException ex) {
                logger.debug("exec took too long attempting to kill process");
                callable.kill();
                logger.debug("process should be dead");
                try {
                    pw = future.get();
                    logger.debug("blocked exec finished");
                } catch (InterruptedException ex1) {
                    logger.error("failed to get PhantomProcess io wrapper", null, ex1);
                } catch (ExecutionException ex1) {
                    logger.error("failed to get PhantomProcess io wrapper", null, ex1);
                }
            }
        } else {
            pw = callable.call();
        }
        pw.closeIOWrappers();
        return pw;
    }

    public void shutdown() {
        if (executor != null) {
            executor.shutdownNow();
        }
    }
}