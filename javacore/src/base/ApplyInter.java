package base;

import java.util.Arrays;

/**
 * 使用接口的案例
 * @author xieziwei99
 * 2019-06-05
 */
public class ApplyInter {

    public static void main(String[] args) {
        String s = "I am yours.";
        process(new UppercaseImpl(), s);
        process(new SplitCaseImpl(), s);
    }

    public static void process(ProcessorInter p, Object input) {
        System.out.println("调用者：" + p.name());
        System.out.println("\t" + p.process(input));
    }
}

interface ProcessorInter {
    String name();

    Object process(Object input);
}

class UppercaseImpl implements ProcessorInter{
    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public Object process(Object input) {
        return ((String)input).toUpperCase();
    }
}

class SplitCaseImpl implements ProcessorInter {
    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public Object process(Object input) {
        return Arrays.toString(((String)input).split(" "));
    }
}
