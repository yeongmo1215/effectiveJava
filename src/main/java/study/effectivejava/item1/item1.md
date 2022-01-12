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
###장점 5가지
1. 이름을 가질 수 있다.
    * 생성자의 경우 Class명과 동일하므로 반활될 객체의 특성을 제대로 설명 하지 못한다.
    * 정적 팩터리 메소드의 경우 이름만 잘지으면 반활될 객체의 특성을 쉽게 묘사할 수 있다.
    * BigInteger(int, int, Random)와 BigInteger.prabablePrime의 차이(값이 소수인 BigInteger를 반환한다의 의미)
    * 생성자의 경우 매개변수로의 차이로만 구현 가능
2.호출될 때마다 인스턴스를 새로 생성하지는 않아도 된다.
  * 불변클래스(immutable class:아이템 17)은 인스턴스를 미리 만들어 놓거나 새로 생성한 인스턴스를 캐싱하여 재활용하는 시긍로 불필요한 객체 생성을 피할 수 있다.
  * 플라이웨이트 패턴(Flyweight pattern)도 이와 비슷한 기법
  * 반복되는 요청에 같은 객체를 반환하는 식으로 언제 어느 인스턴스를 살아 있게 할지 철저히 통제할 수 있다.(=인스턴스 통제(instance-controlled)클래스)
  * 인스턴스를 통제하는 이유
    * 인스턴스를 통제하면 싱클톤(singleton:아이템 3)으로 만들 수도, 인스턴스화 불가(noninstaiable:아이템4)로 만들 수 있다.
    * 또한 불변클래스(immutable class:아이템 17)에서 동치인 인스턴스가 단 하나뿐 임을 보장 할 수 있다.(a ==b 일때만 a.equals(b)가 성립)
    * 인스턴스 통제는 플라이웨이트 패턴(Flyweight pattern)의 근간이 되며, 열거타입:아이템34 는 인스턴스가 하나만 만들어짐을 보장한다.
3. 반환 타입의 하위 타입 객체를 반환할 수 있는 능력이 있다.
   * 반환할 객체의 클래스를 자유롭게 선택할 수 있는 엄청난 유연성 제공(ex. 반환 타입을 상속 받은 하위 클래스)
   * 구현 클래스(하위 클래스)를 공개하지 않고도 그 객체 반환 가능(API를 작게 유지)
   * 인터페이스 기반 핵심 기술
   * 자바 8 부터 인터페이스에서 정적 메서드 구현 가능
4. 입력 매개변수에 따라 매번 다른 클래스의 객체를 반환 할 수 있다.
   * 반환 타입의 하위 타입이기만 한다면 상관없음.(ex. EnumSet 클래스)
   * 3번 장점과 같은 맥락
5. 정적 팩터리 메서드를 작성하는 시점에는 반환할 객체의 클래스가 존재하지 않아도 된다.
   * 서비스 제공자 프레임워크(service provider framework를 만드는 근간이 된다.(ex. JDBC)
     * 구현체의 동작을 정의하는 서비스 인터페이스(service interface)(ex. Connection)
     * 제공자가 구현체를 등록 할 때 사용하는 제공자 등록 API(provider registration API)(ex. DriverManager.registerDriver)
     * 클라이언트가 서비스의 인스턴스를 얻을 때 사용하는 서비스 접근 API(service access API)(ex. DriverManager.getConnection)
     * 추가. 서비스 인터페이스의 인스턴스를 생성하는 팩터리 객체를 설명해주는 서비스 제공자 인터페이스(service provider interface)(ex. Driver)
        * Java에서는 Connection, Driver 인터페이스를 제공
        * Java의 DriverManager 클래스에는 ```static void registerDriver(String name, Driver d)``` 라는 정적 메서드 존재(Driver에서 해당 메서드 호출)
        * Java의 DriverManager 클래스에는 ```static Connection getConnection(String name)``` 라는 정적 팩터리 메서드로 Driver 객체의 Connection 객체 반환.
     
###단점 2가지
1. 상속을 하려면 public이나 protected 생성자가 필요하므로 정적 팩터리 메서드만 제공하면 하위 클래스를 만들 수 없다.
   * 이 제약은 상속보다 컴포지션을 사용하도록 유도하고 불변 타입으로 만들려면 이 제약을 지켜야 한다는 점에서 오히려 장점으로 받아들일 수도 있다.
2. 정적 팩터리 메서드는 프로그래머가 찾기 어렵다.
   * JavaDoc이 생성자처럼 API설명에 알아서 처리해주지 않는다.
   * 널리 알려진 규약을 따라 메서드 이름을 짓는식으로 문제를 완화한다.
        * from : 매개변수를 하나 받아서 해당 타입의 인스턴스를 반환하는 형변환 메서드<br>
     ```Date d = Date.from(instant);```
        * of : 여러 매개변수를 받아 적절한 타입의 인스턴스를 반환하는 집계 메서드<br>
     ```Set<Rank> faceCars = EnumSet.of(JACK, QUEEN, KING)```
        * valueOf : from 과 of의 더 자세한 버전<br>
     ```BigInteger prime = BigInteger.valueOf(Integer.MAX_VALUE)```
        * instance 혹은 getInstance : (매개변수를 받는다면)매개변수로 명시한 인터페이스를 반환하지만, 같은 인스턴스임은 보장하지 않음<br>
     ```StackWalker luke = StackWalker.getInstance(options)```
        * create 혹은 newInstance : 매번 새로운 인스턴스를 생성해 반환함<br>
     ```Object newArray = Array.newInstance(classObject ,arrayLen)```
        * getType : getInstance와 같으나, 생성할 클래스가 아닌 다른클래스에 팩터리 메서드를 정의할때 씀.(Type의 객체 반환)<br>
     ```FileStore fs = Files.getFileStore(path)```
        * newType : newInstance와 같으나, 생성할 클래스가 아닌 다른 클래스에 팩터리 메서드를 정의할때 씀.(Type의 객체 반환)<br>
     ```BufferedReader br = Files.newBufferedReader(path)```
        * type : getType 과 newType의 간결한 버전<br>
     ```List<Complaint> litany = Collections.list(legacyLitany)```
   
#정적 팩터리 메서드를 사용하는게 생성자보다 유리한 경우가 많으므로 무작정 public 생성자를 제공하던 습관 고치기!