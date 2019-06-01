package collection.set;

import java.util.HashSet;

/**
 * HashSet会比较两个对象的equals方法，和hashCode方法
 * @author xieziwei99
 * 2019-05-31
 */
public class SetDemo1 {

    public static void main(String[] args) {
        HashSet s = new HashSet();
        // s中会有两个EqualsCls对象，因为这两个对象hashCode不一样
        s.add(new EqualsCls());
        s.add(new EqualsCls());
        // s中也会有两个HashCodeCls对象，尽管这两个的hash值一样（采用链式结构存储）
        s.add(new HashCodeCls());
        s.add(new HashCodeCls());
        // s中只会有一个HashSetCls对象，因为他们的equals和hashCode都相等
        s.add(new HashSetCls());
        s.add(new HashSetCls());
        System.out.println(s);  // 输出5个object
    }
}

class EqualsCls {
    @Override
    public boolean equals(Object o) {
        return true;
    }
}

class HashCodeCls {
    @Override
    public int hashCode() {
        return 1;
    }
}

class HashSetCls {
    @Override
    public int hashCode() {
        return 2;
    }

    @Override
    public boolean equals(Object obj) {
        return true;
    }
}
