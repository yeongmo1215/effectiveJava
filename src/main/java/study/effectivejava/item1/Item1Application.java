package study.effectivejava.item1;

public class Item1Application {
    public static void main(String[] args) {

//        String ss = "A";
//        ImmutableString imString = new ImmutableString(ss);
//        System.out.println("ss : " + ss.hashCode() + ", ss.toString() : " + ss.toString() + ", imString : " + imString.hashCode() + ",  imString.toString() : " + imString.toString());
//        ss.concat("B");
//        String sss = "A";
//        System.out.println("sss : " + sss.hashCode() + ", sss.toString() : " + sss.toString() + ", imString : " + imString.hashCode() + ",  imString.toString() : " + imString.toString());
//        imString = new ImmutableString(sss);
//        System.out.println("ss : " + ss.hashCode() + ", ss.toString() : " + ss.toString() + ", imString : " + imString.hashCode() + ",  imString.toString() : " + imString.toString());

//        Integer a = 10; //스택 메모리에 레퍼런스 변수 a 할당, 힙 메모리에 Integer 10 할당
//        System.out.println("Main a before value: " + a + ", Main a before address :" + a.hashCode());
//        changeInteger(a); //메소드 시작시 스택 메모리에 레퍼런스 새로운 변수 param 할당, 메소드 종료시 스택 메모리의 param 할당 종료 및 a는 유지
//        System.out.println("Main a after 1 value: " + a + ", Main a after 1 address :" + a.hashCode());
//        a += 10;    // 스택 메모리에 할당된 a 변수의 주소값 변경(힙메모리의 새로 할당된 Integer 20)
//        System.out.println("Main a after 2 value: " + a + ", Main a after 2 address :" + a.hashCode());

        String s = "hello";
        System.out.println("Main s before value: " + s + ", Main s before address :" + s.hashCode());
        changeString(s);
        System.out.println("Main s after 1 value: " + s + ", Main s after 1 address :" + s.hashCode());
        s += " world";
        System.out.println("Main s after 2 value: " + s + ", Main s after 2 address :" + s.hashCode());
    }

//    public static void changeInteger(Integer param) {
//        System.out.println("Methid param before value: " + param + ", Methid param before address :" + param.hashCode());
//        param += 10;    //스택 메모리의 할당된 param 변수의 주소값 변경, 힙 메모리에 새로운 Integer 20 할당(기존 Integer 10 유지(GC))
//        System.out.println("Methid param after value: " + param + ", Methid param after address :" + param.hashCode());
//    }
    public static void changeString(String param) {
        System.out.println("Methid param before value: " + param + ", Methid param before address :" + param.hashCode());
        param += " world";
        System.out.println("Methid param after value: " + param + ", Methid param after address :" + param.hashCode());
    }
}
