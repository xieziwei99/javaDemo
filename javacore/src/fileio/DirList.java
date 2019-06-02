package fileio;

import java.io.File;
import java.util.Arrays;

/**
 * 列出当前根目录下所有目录或文件
 * @author xieziwei99
 * 2019-06-02
 */
public class DirList {

    public static void main(String[] args) {
        File path = new File("E:\\自定义 Office 模板");
        // 列出当前目录下所有目录或文件名
        // 因为此list()方法返回的大小固定，故返回固定大小且效率更高的数组
        String[] list = path.list();
        if (list != null) {
            Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);   // 忽略大小写的排序
            for (String s : list) {
                System.out.print(s + "\t");
            }
        }
    }
}
