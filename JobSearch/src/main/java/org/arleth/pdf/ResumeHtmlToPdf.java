package org.arleth.pdf;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.arleth.process.PhantomJsProcessWrapper;
import org.arleth.process.ProcessWrapper;

/**
 *
 * @author arleth
 */
public class ResumeHtmlToPdf {

    PhantomJsProcessWrapper phantomJs;
    File outputFolder;

    public class ResumePdf {

        public byte[] pdfBytes;
        public File pdf;
    }

    public ResumeHtmlToPdf(String phantomJsFile, String output) {
        if (phantomJsFile == null) {
            phantomJsFile = "d:/projects/phantomjs-1.5.0-win32-static/phantomjs.exe";
        }
        this.phantomJs = new PhantomJsProcessWrapper(new File(phantomJsFile));
        this.outputFolder = new File(output);
    }

    public ResumePdf toPdf(String id, long time) throws IOException {
        ResumePdf result = new ResumePdf();
        result.pdf = new File(outputFolder, id + "_" + time + ".pdf");
        if (result.pdf.exists()) {
            readBytes(result);
        } else {
            File rasterize = new File(outputFolder, "rasterize.js");
            String[] args = new String[]{
                rasterize.getAbsolutePath(),
                id,
                "" + time,
                outputFolder.getAbsolutePath()
            };

            ProcessWrapper pw = phantomJs.execBlocked(args);
            try {
                if (result.pdf.exists()) {
                    readBytes(result);
                }
            } finally {
                pw.stdouterr();
                pw.closeIOWrappers();
            }
        }
        return result;
    }

    private void readBytes(ResumePdf resumePdf) throws IOException {
        int size = (int) resumePdf.pdf.length();
        resumePdf.pdfBytes = new byte[size];
        FileInputStream fis = new FileInputStream(resumePdf.pdf);
        BufferedInputStream bis = new BufferedInputStream(fis);
        for (int i = 0; i < size; i++) {
            int next = bis.read();
            resumePdf.pdfBytes[i] = (byte) next;
        }
        bis.close();
        fis.close();
    }

    public static void main(String[] args) throws Exception {
        ResumeHtmlToPdf test = new ResumeHtmlToPdf(null, "d:/");
        ResumeHtmlToPdf.ResumePdf resumePdf1 = test.toPdf("4fdf3c38914f1a6c3a330396", 43210);
        System.out.println("1");
        ResumeHtmlToPdf.ResumePdf resumePdf2 = test.toPdf("4fdf3c38914f1a6c3a330396", 43211);
        System.out.println("2");
        ResumeHtmlToPdf.ResumePdf resumePdf3 = test.toPdf("4fdf3c38914f1a6c3a330396", 43212);
        System.out.println("3");
        ResumeHtmlToPdf.ResumePdf resumePdf4 = test.toPdf("4fdf3c38914f1a6c3a330396", 43213);
        System.out.println("4");
        //System.out.println("pdf bytes " + resumePdf.pdfBytes.length);
        test.phantomJs.shutdown();
        System.out.println("shutdown!");
    }
}