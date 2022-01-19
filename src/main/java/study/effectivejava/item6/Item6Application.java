package study.effectivejava.item6;

import study.effectivejava.item5.Bar;
import study.effectivejava.item5.Foo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Item6Application {
    public static void main(String[] args) {
        long sum = 0L;
        for(long i = 0; i <= Integer.MAX_VALUE; i++){
            sum += i;   //LONG 인스턴스 매번 새로 생성
        }
    }
}
