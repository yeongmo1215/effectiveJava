package study.effectivejava.item11;

public class Item11Application {
    public static void main(String[] args) {

        PhoneNumber phoneNumber = new PhoneNumber((short) 31, (short) 8, (short) 1);

        PhoneNumber phoneNumber1 = new PhoneNumber((short) 31, (short) 8, (short) 1);
        PhoneNumber phoneNumber2 = new PhoneNumber((short) 31, (short) 8, (short) 1);
        System.out.println(phoneNumber.hashCode());
        System.out.println(phoneNumber1.hashCode());
        System.out.println(phoneNumber2.hashCode());
    }
}
