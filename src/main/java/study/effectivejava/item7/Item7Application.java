package study.effectivejava.item7;

import java.util.LinkedHashMap;
import java.util.WeakHashMap;

public class Item7Application {
    public static void main(String[] args) {
        WeakHashMap<Integer, String> map = new WeakHashMap<>();
        Integer key1 = 1000;
        Integer key2 = 2000;
        String value1 = "aaa";
        String value2 = "bbb";
//        Integer value1 = Integer.valueOf(1000);
//        Integer value2 = Integer.valueOf(2000);

        map.put(key1, value1);
        map.put(key2, value2);

        key1 = null;

        System.gc();

        map.entrySet().forEach(el -> System.out.println(el));
    }
}
