# ITEM 1 생성자 대신 정적 팩터리 메서드를 고려하라

클라이언트가 클래스의 인스턴스를 얻는 수단
* public 생성자
* 정적 팩터리 메서드(디자인 패턴이 아닌 아래와 같은 단순 정적 메서드)
```java
public static Boolean valueOf(boolean b){
    return b ? Boolean.true : Boolean.false;
        }
```

## 정적 팩터리 메서드(static factory method)
장점
* 이름을 가질 수 있다.
    * 생성자의 경우 Class명과 동일하므로 반활될 객체의 특성을 제대로 설명 하지 못한다.
    * 정적 팩터리 메소드의 경우 이름만 잘지으면 반활될 객체의 특성을 쉽게 묘사할 수 있다.
    * BigInteger(int, int, Random)와 BigInteger.prabablePrime의 차이(값이 소수인 BigInteger를 반환한다의 의미)
    * 생성자의 경우 매개변수로의 차이로만 구현 가능
* 호출될 때마다 인스턴스를 새로 생성하지는 않아도 된다.
  * 불변클래스(immutable class:아이템 17)은 인스턴스를 미리 만들어 놓거나 새로 생성한 인스턴스를 캐싱하여 재활용하는 시긍로 불필요한 객체 생성을 피할 수 있다.
  * 플라이웨이트 패턴(Flyweight pattern)도 이와 비슷한 기법
  * 반복되는 요청에 같은 객체를 반환하는 식으로 언제 어느 인스턴스를 살아 있게 할지 철저히 통제할 수 있다.(=인스턴스 통제(instance-controlled)클래스)
  * 인스턴스를 통제하는 이유
    * 인스턴스를 통제하면 싱클톤(singleton:아이템 3)으로 만들 수도, 인스턴스화 불가(noninstaiable:아이템4)로 만들 수 있다.
    * 또한 불변클래스(immutable class:아이템 17)에서 동치인 인스턴스가 단 하나뿐 임을 보장 할 수 있다.(a ==b 일때만 a.equals(b)가 성립)
    * 인스턴스 통제는 플라이웨이트 패턴(Flyweight pattern)의 근간이 되며, 열거타입:아이템34 는 인스턴스가 하나만 만들어짐을 보장한다.
* 