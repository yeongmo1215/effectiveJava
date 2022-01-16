package study.effectivejava.item5;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Item5Application {
    public static void main(String[] args) {
        List<Foo> fooList = new ArrayList<>();
        fooList.add(new Foo());
        List<Bar> barList = new ArrayList<>();
        barList.add(new Bar());

        test1(fooList);
        test1(barList);

//        test2(fooList);     //error
        test2(barList);

        test3(fooList);
//        test3(barList);     //error

        test4(fooList);
        test4(barList);

        test5(fooList);
        test5(barList);

//        test6(fooList);      //error
        test6(barList);
    }

    private static void test1(List<? extends Foo> fooList) {
        Objects.requireNonNull(fooList).get(0).foo();
//        Objects.requireNonNull(fooList).get(0).bar(); //error
        Foo firstItem = Objects.requireNonNull(fooList).get(0);
    }

    private static void test2(List<? extends Bar> fooList) {
        Objects.requireNonNull(fooList).get(0).foo();
        Objects.requireNonNull(fooList).get(0).bar();
        Bar firstItem = Objects.requireNonNull(fooList).get(0);
    }

    private static void test3(List<? super Foo> fooList) {
        //super의 경우 Object로 간주
//        Objects.requireNonNull(fooList).get(0).foo(); //error
        Objects.requireNonNull(fooList).get(0);
    }

    private static void test4(List<? super Bar> fooList) {
        //super의 경우 Object로 간주
//        Objects.requireNonNull(fooList).get(0).foo(); //error
        Objects.requireNonNull(fooList).get(0);
    }
    //Generic은 데이터에 초점을 두고 와일드카드는 메소드에 초점을 둠.
    private static <T extends Foo> void test5(List<T> fooList) {
        T firstItme = Objects.requireNonNull(fooList).get(0);
        firstItme.foo();
//        firstItme.bar();  //error
    }

    private static <T extends Bar> void test6(List<T> fooList) {
        T firstItme = Objects.requireNonNull(fooList).get(0);
        firstItme.foo();
        firstItme.bar();
    }
}
