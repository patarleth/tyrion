package org.arleth.process;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author arleth
 */
public class IOWrapper implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(IOWrapper.class);
    BufferedReader is;
    StringBuilder sb = null;
    StringBuffer all;
    private ArrayList<String> list = null;

    public IOWrapper(StringBuffer all, InputStream is) {
        this(all, is, true);
    }

    public IOWrapper(StringBuffer all, InputStream is, boolean stdOutAsList) {
        this.all = all;
        this.is = new BufferedReader(new InputStreamReader(is));
        if (stdOutAsList) {
            list = new ArrayList<String>();
        }
    }

    @Override
    public void run() {
        try {
            String next = is.readLine();
            while (next != null) {
                if (list != null) {
                    list.add(next);
                }
                all.append(next).append("\n");
                if (sb == null) {
                    sb = new StringBuilder();
                }
                sb.append(next).append("\n");
                next = is.readLine();
            }
        } catch (IOException ioe) {
            logger.error("could not read next line from process", ioe);
        }
    }

    public void close() {
        try {
            is.close();
        } catch (IOException ex) {
        }
    }

    @Override
    public String toString() {
        String result = (sb == null) ? null : sb.toString();
        return result;
    }

    public List<String> getList() {
        return list;
    }
}