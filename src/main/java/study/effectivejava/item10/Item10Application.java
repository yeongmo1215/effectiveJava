package study.effectivejava.item10;

import java.util.AbstractList;

public class Item10Application {
    public static void main(String[] args) {

        PhoneNumber phoneNumber = new PhoneNumber((short) 31, (short) 8, (short) 1);

        PhoneNumber phoneNumber1 = new PhoneNumber((short) 31, (short) 8, (short) 1);
        PhoneNumber phoneNumber2 = new PhoneNumber((short) 31, (short) 8, (short) 1);
        System.out.println(phoneNumber.equals(phoneNumber1));
        System.out.println(phoneNumber1.equals(phoneNumber));
        System.out.println(phoneNumber1.equals(phoneNumber2));
        System.out.println(phoneNumber.equals(phoneNumber2));

    }
}
