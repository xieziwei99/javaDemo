/**
 * @Description 测试类SimpleDateFormat的使用
 * @author xieziwei99
 * @create 2019-05-14
 */
package simpleDateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String formatDate(Date date) {
        return sdf.format(date);
    }

    public static Date parse(String strDate) throws ParseException {
        return sdf.parse(strDate);
    }

    public static void main(String[] args) {
        System.out.println(new Date());
        System.out.println(formatDate(new Date()));
        try {
            System.out.println(parse(formatDate(new Date())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
