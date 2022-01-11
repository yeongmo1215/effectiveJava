# ITEM 1 생성자 대신 정적 팩터리 메서드를 고려하라

###객체(object)
* 속성과 행동으로 구성되어 있는 하나의 단위
* 프로그래밍으로 표현하려는 궁극적인 실체
### 클래스(class)
* 객체를 만드는 설계도
### 인스턴스(instance)
* 클래스를 메모리에 생성한 객체
* 독립적

### 기본형 타입(Primitive type)
* 총 8가지 타입(byte, short, int, long, float, double, char, boolean)
* 기본값이 있기 때문에 Null이 존재하지 않음. 만약 Null을 넣고 싶다면 래퍼 클래스 화용
* 실제 값을 저장하는 공간으로 스택(Stack) 메모리(GC 대상 아님)에 저장
* 만약 컴파일 시점에 담을 수 있는 크기를 벗어나면 에러 발생(컴파일 에러)
### 래퍼 클래스(Wrapper class)
* 프로그램에 따라 기본형 타입의 데이터를 객체로 취급해야 하는 경우가 있음
* 예를들어 메소드의 인수로 객체 타입만이 요구되면, 기본형 타입의 데이터를 사용 할 수 없음. 이때 기본형 타입의 데이터를 먼저 객체로 변환 후 작업 수행
* 8개의 기본 타입에 해당하는 데이터를 객체로 포장해주는 클래스를 래퍼 클래스 라고함(Byte, Short, Integer, Long, Float, Double, Character, Boolean)
* 래퍼 클래스는 각각의 타입에 해당하는 데이터를 인수로 전달받아 객체 생성
```java
Integer num = new Integer(17);  //박싱
int n = num.intValue();         //언박싱
```
* java.lang 패키지에 포함
### 박싱(Boxing)
기본 자료형(primitive type)의 데이터를 대응되는 래퍼 클래스(Wrapper class)로 만드는 동작
### 언박싱(Unboxing)
래퍼 클래스(Wrapper class)에서 기본 자료형(primitive type)으로 만드는 동작
### 오토 박싱/오토 언박싱
JDK 1.5부터 박싱과 언박싱이 필요한 경우 자바 컴파일러가 자동으로 처리
```java
Integer num = 17;   //오토 박싱
int n = num;        //오토 언박싱
```

### 스택 메모리
컴파일 시 결정되는 기본형 타입이 저장되는 공간
지역변수, 매개변수, 리턴값, 참조변수 등이 저장
메서드가 호출될 때, 메모리에 FILO로 하나씩 생성
메서드가 끝날 떄, LIFO로 하나씩 제거
메서드 호출시마다 각각의 스택 프레임(그 메서드만의 방)이 생성
보존기간 : {} 또는 메서드가 끝날때 까지

### 힙 메모리
런타입시 결정되는 참조형 데이터 타입이 저장되는 공간
new 연산자를 통해 객체가 생성되는 공간
보존기간 : 객체가 더이상 안쓰이거나 명시적인 Null 선언시 GC 대상

### 메서드 메모리
가장 먼저 데이터가 저장되는 공간?
클래스 로더에 의해 로딩된 클래스, 메서드, 클래스 변수(static), 전역변수가 저장
클래스변수나 전역변수를 무분별하게 많이 사용하면 메모리가 부족할 수 있음
보존기간 : 프로그램의 시작부터 종료까지 메모리에 남음, 명시적인 Null 선언시 GC 청소대상

### 참조형 데이터 타입(Reference type)
기본형 타입을 제외한 타입들이 모두 참조형 타입이다(래퍼 클래스도 포함)(Array, Enumeration, Class, Interface)
빈 객체를 의미하는 Null이 존재
값이 저장되어 있는 곳의 주소값을 저장하는 공간으로 힙 메모리에 저장
문법상으로는 에러가 없지만 실행시켰을때 에러가 나는 런타입 에러 발생가능(NullPointException)

### 불변 클래스(immutable class)
변경이 불가능한 클래스이며, 가변적이지 않은 클래스
만들어진 Class는 참조형 데이터 타입으로 힙 메모리에 저장됨
대표적으로 래퍼 클래스(Wrapper Class), String 클래스..
* 장점
  * 멀티 스레드 환경에서 동기화 처리없이 객체 유지(Thread-safe)
* 단점
  * 객체가 가지는 값마다 새로운 객체가 생성되므로 메모리 누수 발생 가능성
```java
    public static void main(String[] args) {
        String s = "hello";
        System.out.println("Main s before value: " + s + ", Main s before address :" + s.hashCode());
        changeString(s);
        System.out.println("Main s after 1 value: " + s + ", Main s after 1 address :" + s.hashCode());
        s += " world";
        System.out.println("Main s after 2 value: " + s + ", Main s after 2 address :" + s.hashCode());
    }
    
    public static void changeString(String param) {
        System.out.println("Methid param before value: " + param + ", Methid param before address :" + param.hashCode());
        param += " world";
        System.out.println("Methid param after value: " + param + ", Methid param after address :" + param.hashCode());
    }
```

Main s before value: hello, Main s before address :99162322<br>
Method param before value: hello, Methid param before address :99162322<br>
Method param after value: hello world, Methid param after address :1794106052<br>
Main s after 1 value: hello, Main s after 1 address :99162322<br>
Main s after 2 value: hello world, Main s after 2 address :1794106052<br>
### 플라이웨이트 패턴(Flyweight pattern)

### 인스턴스 통제

### 싱글톤

### 인스턴스화 불가