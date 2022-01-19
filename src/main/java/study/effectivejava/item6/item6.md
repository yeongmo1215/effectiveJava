# ITEM 6 불필요한 객체 생성을 피하라

똑같은 기능의 객체를 매번 생성하기보다는 객체 하나를 재사용하는 편이 나을 때가 많다.
###나쁜예 1
* ```java 
  String s = new String("123");   //해당 문장이 실행될때마다 String 인스턴스 새로 생성
  Boolean b = new Boolean(null);  //해당 문장이 실행될때마다 Boolean 인스턴스 새로 생성
  ```
생성자 대신 정적 팩터리 메서드를 제공하는 불변클래스는 정적 팩터리 메서드를 사용해 불필요한 객체 생성 피할 수 있다.
Boolean(String) 생성자 대신 Boolean.valueOf(String)(자바 9에서 사용자제(deprecated) API로 지정됨.)
###좋은예 1
* ```java 
  String s = "123";
  Boolean b = Boolean.valueOf(null);
  ```

###나쁜예 2
* ```java
  public class RomanNumerals {
    static boolean isRomanNumeral(String s){
        // 내부적으로 생성되는 Pattern 인스턴스는 한번 쓰고 버려져서 곧바로 가비지 컬렉터 대상이 됨.
        return s.matches("^(?=.)ㅡ*(C[MD]|D?C{0,3}(CX[CL]L?X{0,3}(I[XV]}V?I{0,3})$");
    }
  }  
  ```
  
String.matches는 정규표현식으로 문자열 형태를 확인하는 가장 쉬운 방법이지만, 성능이 중요한 경우에 반복해서 사용하기엔 적합하지 않음.
### 좋은예 2
* ```java
   import java.util.regex.Pattern;   
  
   public class RomanNumerals {
        private static final Pattern ROMAN = Pattern.compile("^(?=.)ㅡ*(C[MD]|D?C{0,3}(CX[CL]L?X{0,3}(I[XV]}V?I{0,3})$"); 
        static boolean isRomanNumeral(String s){
            return ROMAN.matcher(s).matches();
        }
      }  
  ```
위에서 해당 메소드가 호출되지 않는다면 ROMAN 필드가 쓸데없이 초기화 되었지만 메서드가 처음 호출 될때 필드를 초기화 하는 지연 초기화(lazy inialization)은 권하지 않음(코드 복잡도 증가, 성능 크게 개선되지 않음) 

###나쁜예 3
* ```java
  private static long sum() {
    Long sum = 0L;
    for(long i = 0; i <= Integer.MAX_VALUE; i++){
        sum += i;   //LONG 인스턴스 매번 새로 생성
    }
    return sum;
  }
  ```

오토박싱은 기본 타입과 그에 대응하는 박싱된 기본 타입의 구분을 흐려주지만, 완전히 없애주는 것은 아니다.
박싱된 기본 타입 보다는 기본 타입을 사용하고, 의도치 않은 오토박싱이 숨어들지 않도록 주의

* ```java
  private static long sum() {
    long sum = 0L;
    for(long i = 0; i <= Integer.MAX_VALUE; i++){
        sum += i;
    }
    return sum;
  }
  ```