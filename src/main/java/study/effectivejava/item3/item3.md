# ITEM 3 private 생성자나 열거 타입으로 싱글턴임을 보증하라

싱글턴(singleton)이란 인터페이스를 오직 하나만 생성할 수 있는 클래스(ex. 함수와 같은 무상태(stateless)객체나 설계상 유일해야 하는 컴포넌트)
단점은 이를 사용하는 클라이언트를 테스트하기가 어려워질 수 있다. 
타입을 인터페이스로 정의한 다음 그 인터페이스를 구현해서 만든 싱글턴이 아니라면 싱글턴 인터페이스를 가짜(mock)구현으로 대체할 수 없다.

###방식 1
public static final 방식
private 생성자는 public static fianl 필드은 Elvis.INSTANCE를 초기화 할때 딱 한번만 호출됨.
장점
1. 해당 클래스가 싱글턴임이 API에 명백히 드러남
2. 간결함
```java
public class Elvis{
    public static final Elvis INSTANCE = new Elvis();
    private Elvis(){
        
    }
    //TODO : ??
    public void leaveTheBuilding(){
        
    }
}
```

###방식 2
정적 팩터리 메서드를 public static 멤버로 제공한다.
장점
1. API를 바꾸지 않고도 싱글턴이 아니게 변경 가능(아이템 1 참고)
2. 정적 팩터리를 제네릭 싱글턴 팩터리로 만들 수 있음
3. 정적 팩터리의 메서드 참조를 공급자(supplier)로 사용 할 수 있다.(ex. supplier<Elvis>)
```java
public class Elvis{
    private static final Elvis INSTANCE = new Elvis();
    private Elvis(){
        
    }
    public static Elvis getInstance(){return INSTANCE;}
    
    public void leaveTheBuilding(){

    }
}
```

####방식 1, 2 의 단점
1. 리플렉션 API(AccessibleObject.setAccessible)를 사용해 private 생성자를 호출할 수 있다. 이러한 공격을 방어하려면 생성자를 수정하여 두번째 객체가 생성되려 할때 예외를 던지게 하면 된다.
2. 직렬화 하려면 모든 인스턴스 필드를 일시적(transient)라고 선언하고 readResolve 메서드를 제공해야 한다. 이렇게 하지 않으면 직렬화된 인스턴스를 역직렬화 할때마다 새로운 인스턴스가 생성됨.
```java
//싱글턴임을 보장해주는 readResolve 메서드
private Object readResolve(){
    //진짜 Elvis를 반환하고, 가짜 Elvis는 가비지 컬렉터에 맡긴다.
    return INSTANCE;
}
```

###방식 3
열거타입(가장 좋은 방법이다)
대부분 상황에서는 원소가 하나인 열거 타입이 싱글턴을 만드는 가장 좋은 방법!
장점
1. 간결
2. 직렬화
3. 리플렉션 공격에도 단 하나의 인스턴스 보장
단 싱글턴이 Enum 외의 클래스를 상속해야 한다면 이방법은 사용 할 수 없음(열거 타입이 다른 인터페이스를 구현하도록 선언할 수는 있다.)??
```java
public enum Elvis{
    INSTANCE;
    
    public void leaveTheBuilding(){}
}
```