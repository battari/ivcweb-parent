package au.com.attari.ivcweb.task.utils;

import java.io.*;

public class FileUtils {

    /**
     * Find files from directory
     * @param inPath
     * @return
     */
    public String[] getFileList(String inPath) {

        File dir = new File(inPath);
        FilenameFilter filter = new MyFilenameFilter();
        String[] children = dir.list(filter);

        return children;

    }

    public class MyFilenameFilter implements FilenameFilter {
        public boolean accept(File inPath, String name) {
            File f = new File(inPath, name);
            if(f.isDirectory()) return false;
            return true;
        }
    }

}
