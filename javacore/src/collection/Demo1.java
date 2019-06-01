package collection;

import java.util.*;

/**
 * List，Set，Map简单实用示例
 * @author xieziwei99
 * 2019-05-23
 */
public class Demo1 {

    public static void main(String[] args) {
        printList();
        printSet();
        printMap();
    }

    private static void printList() {
        List<String> arrayList = new ArrayList<>();
        arrayList.add("List");
        arrayList.add("Set");
        arrayList.add("Queue");
        arrayList.add("Map");
        System.out.println("ArrayList elements: " + arrayList);

        List<String> linkedList = new LinkedList<>();
        linkedList.add("List");
        linkedList.add("Set");
        linkedList.add("Queue");
        linkedList.add("Map");
        System.out.println("LinkedList elements: " + linkedList);
    }

    private static void printSet() {
        Set<String> hashSet = new HashSet<>();
        hashSet.add("List");
        hashSet.add("Set");
        hashSet.add("Queue");
        hashSet.add("Map");
        System.out.println("HashSet elements: " + hashSet);

        Set<String> treeSet = new TreeSet<>();
        treeSet.add("List");
        treeSet.add("Set");
        treeSet.add("Queue");
        treeSet.add("Map");
        System.out.println("TreeSet elements: " + treeSet);
    }

    private static void printMap() {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("List", "ArrayList");
        hashMap.put("Set", "HashSet");
        hashMap.put("Queue", "PriorityQueue");
        hashMap.put("Map", "HashMap");
        System.out.println("HashMap elements: " + hashMap);

        Map<String, String> treeMap = new TreeMap<>();
        treeMap.put("List", "ArrayList");
        treeMap.put("Set", "HashSet");
        treeMap.put("Queue", "PriorityQueue");
        treeMap.put("Map", "HashMap");
        System.out.println("TreeMap elements: " + treeMap);
    }
}
