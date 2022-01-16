# ITEM 4 인스턴스화를 막으려거든 private 생성자를 사용하라

단순히 정적 메서드와 정적 필드만을 담은 클래스를 만들때
* 기본 타입값이나 배열 관련 메서드를 모아 놓는경우(ex. java.lang.Math, java.util.Arrays)
* 특정 인터페이스를 구현하는 객체를 생성해주는 정적 메서드(혹은 팩터리)를 모아두는 경우(ex. java.utils.Collections)
* final 클래스와 관련한 메서드를 모아놓을때(final 클래스를 상속 불가능)

생성자를 명시하지 않으면 컴파일러가 자동으로 기본 생성자(매개변수 없는 public)를 만들어줌

추상클래스를 만드는것으로는 인스턴스화를 막을수 없음
* 하위클래스를 만들어 인스턴스화 가능
* 추상클래스를 보고 상속해서 쓰라는 뜻으로 오해할 수 있음

private 생성자를 추가하여 클래스의 인스턴스화를 막을수 있다!
```java
public class UtilityClass{
    //기본 생성자가 만들어지는것을 막는다.
    private UtilityClass(){
        throw new AssertionError();
    }
}
```


