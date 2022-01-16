# ITEM 5 자원을 직접 명시하지 말고 의존 객체 주입을 사용하라

많은 클래스가 하나 이상의 자원에 의존한다.(ex. 맞춤법 검사기(SpellCheker)는 사전(dictionary)에 의존)
##잘못된 예
###정적 유틸리티 클래스
```java
public class SpellChecker{
    private static final Lexicon dictionary = ...;
    
    //객체 생성 방지
    private SpellChecker(){
        throw new AssertionError();    
    }
    public static boolean isValid(String word){}
    public static List<String> suggestions(String typo){}
}
```
###싱글턴
```java
public class SpellChecker{
    private final Lexicon dictionary = ...;
    private SpellChecker(){
        
    }
    public static SpellChecker INSTANCE = new SpellChecker();
    public boolean isValid(String word){}
    public List<String> suggestions(String typo){}
}
//enum 형식 
public enum SpellChecker{
    INSTANCE;
    private final Lexicon dictionary = ...;
    public boolean isValid(String word){}
    public List<String> suggestions(String typo){}
}

```
정적 멤버로 사전을 정의하거나 싱글턴으로 사용하는경우 단점 유연하지 않고(단 하나의 사전만 사용한다고 가정하였기 때문) 테스트하기 어려움
final 한정자를 제거하고 다른 사전으로 교체하는 메서드를 추가하여 사용할 수 있지만 어색하고? 오류를내기 쉬우며 멀티스레드 환경에서는 쓸 수 없다.
사용하는 자원에 따라 동작이 달라지는 클래스에는 정적 유틸리티 클래스나 싱글턴 방식은 적합하지 않음

클래스(SpellChecker)가 여러 자원 인스턴스를 지원해야 하며, 클라이언트가 원하는 자원(dictionary)을 사용해야 하는 경우 인스턴스를 생성할때 생성자에 필요한 자원을 넘겨주는(의존 객체 주입) 방식 사용
###의존객체주입
```java
import java.util.Objects;

public class SpellChecker {
    private final Lexicon dictionary;

    public SpellChecker(Lexicon dictionary) {
        this.dictionary = Objects.requireNonNull(dictionary);
    }
    
    public boolean isValid(String word){}
    public List<String> suggestions(String typo){}
}
```
불변(immutable)을 보장하고 같은 자원을 사용하려는 여러 클라이언트가 의존 객체들을 안심하고 공유할 수 있다.
생성자에 자원 팩터리를 넘겨주는 방식도 있음.
팩터리랑ㄴ 호출할 때마다 특정 타입의 인스턴스를 반복해서 만들어주는 객체(팩터리 메서드 패턴)(ex. Supplier<T> 인스턴스)

# 클래스가 내부적으로 하나 이상의 자원에 의존하고, 그 자원이 클래스 동작에 영향을 준다면 싱글턴과 정적 유틸리티 클래스는 사용하지 않는것이 좋다. 이 자원들은 클래스가 직접 만들게 해서도 안된다. 대신 필요한 자원을(혹은 그 자원을 만들어주는 팩터리를) 생성자에 (혹은 정적 팩터리나 빌더에)넘겨주자. 의존 객체 주입이라 하는 이 기법은 클래스의 유연성, 재사용성, 테스트 용이성을 기막히게 개선해준다.