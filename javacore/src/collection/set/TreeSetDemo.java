package collection.set;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * 红黑树
 * @author xieziwei99
 * 2019-06-01
 */
public class TreeSetDemo {

    public static void main(String[] args) {
        TreeSet<Bird> birds = new TreeSet<>();
        birds.add(new Bird(2));
        birds.add(new Bird(3));
        birds.add(new Bird(1));

        Iterator<Bird> it = birds.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}

// 要用TreeSet则必须implements Comparable接口
class Bird implements Comparable<Bird> {

    int size;

    public Bird(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return size + "号鸟";
    }

    @Override
    public int compareTo(Bird o) {
        return size - o.size;
    }
}
