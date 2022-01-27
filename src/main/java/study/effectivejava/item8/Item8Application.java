package study.effectivejava.item8;

import java.util.WeakHashMap;

public class Item8Application {
    public static void main(String[] args) {
        try(Room room = new Room(10)){
            System.out.println("TRY~");
        } catch (Exception e) {
            e.printStackTrace();
        }

//        new Room(10);
//        System.gc();
//        System.out.println("TRY~");

    }
}
