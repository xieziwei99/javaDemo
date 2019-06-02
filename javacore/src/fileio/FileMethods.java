package fileio;

import java.io.File;

/**
 * 文件相关方法
 * @author xieziwei99
 * 2019-06-02
 */
public class FileMethods {

    public static void main(String[] args) {
        File f = new File("C:\\Users\\xieziwei99\\Desktop");
        System.out.println("绝对路径：" + f.getAbsolutePath()
                        + "\n是否可读：" + f.canRead()
                        + "\n是否可写：" + f.canWrite()
                        + "\n文件名：" + f.getName()
                        + "\n上级目录：" + f.getParent()
                        + "\n相对地址：" + f.getPath()
                        + "\n文件长度：" + f.length()
                        + "\n最近修改时间：" + f.lastModified());
        if (f.isFile()) {
            System.out.println("是一个文件");
        } else if (f.isDirectory()) {
            System.out.println("是一个目录");
        }
    }
}
