package study.effectivejava.item12;

import java.math.BigInteger;

public class Item12Application {
    public static void main(String[] args) {
        PhoneNumber pn = new PhoneNumber((short) 123, (short) 333, (short) 4444);
        System.out.println(pn.toString());
    }
}
