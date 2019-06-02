package fileio;

import java.io.File;
import java.io.FilenameFilter;

/**
 * @author xieziwei99
 * 2019-06-02
 */
public class MyFilenameFilter {

    public static void main(String[] args) {
        File f = new File("E:\\IDEA\\javaDemo\\javacore\\src\\fileio");
        MyFilter filter = new MyFilter("s.java");
        String[] files = f.list(filter);
        for (String s : files) {
            System.out.println(s);
        }
    }

    static class MyFilter implements FilenameFilter {
        private String type;

        public MyFilter(String type) {
            this.type = type;
        }

        @Override
        public boolean accept(File dir, String name) {
            return name.endsWith(type);
        }
    }
}
