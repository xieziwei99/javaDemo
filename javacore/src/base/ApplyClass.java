package base;

import java.util.Arrays;

/**
 * 使用继承的案例
 * @author xieziwei99
 * 2019-06-05
 */
public class ApplyClass {

    public static void main(String[] args) {
        String s = "I am Yours.";
        process(new Uppercase(), s);
        process(new SplitCase(), s);
    }

    public static void process(Processor p, Object in) {
        System.out.println("调用者：" + p.name());
        System.out.println("\t" + p.process(in));
    }
}

class Processor {
    public String name() {
        return this.getClass().getSimpleName();
    }

    Object process(Object in) {
        return in;
    }
}

class Uppercase extends Processor {
    @Override
    Object process(Object in) {
        return ((String)in).toUpperCase();
    }
}

class SplitCase extends Processor {
    @Override
    Object process(Object in) {
        return Arrays.toString(((String)in).split(" "));
    }
}
