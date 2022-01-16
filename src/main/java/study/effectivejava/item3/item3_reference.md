# ITEM 3 private 생성자나 열거 타입으로 싱글턴임을 보증하라

### Mock
단위 테스트를 하기 위해서 특정 메서드 호출을 하기위해 다른 네트워크, 데이터베이스 등등 제어하기 어려운 것들에 의존하고 있는 경우 테스트가 힘들다. 
실제 객체를 만들어 사용하기에 시간, 비용등의 Cost가 높거나 혹은 객체 서로간의 의존성이 강해 구현하기 힘든경우 가짜 객체를 만들어 사용하는 방법
#### Mock이 필요한 경우
* 테스트 작성을 위한 환경 구축이 어려운 경우
* 테스트가 특정 경우나 순간에 의존적인 경우
* 테스트 시간이 오래걸리는 경우
* 개인 PC의 성능이나 서버의 성능문제로 오래걸릴 수 있는 경우 시간을 단축하기 위해 사용

### Test Stub
필요한 인터페이스에 대한 구현 객체로 마치 실제로 동작하는 것처럼 보이게 만들어 놓은 객체
객체의 특정상태를 가정해서 만들어 특정값을 리턴해 주거나 특정 메세지를 출력 해주는 작업을 함 >> Hard coding 되기 때문에 로직에 따른 값 변경은 테스트 불가
즉, 어떤 행위가 호출되면 특정 값을 리턴시켜주는 형태


###무상태 객체(stateless obejct)
인스턴스 필드가 없는 클래스의 인스턴스
```java
//무상태 객체
class Stateless1{
    void test(){
        System.out.println("TEST!!");
    }
}

//무상태 객체
class Stateless2{
    final String TEST = "TEST!!";
    void test(){
        System.out.println(TEST);
    }
}

//상태 객체
class Immutable {
    final String testString;
    Immutable(String testString){
        this.testString = testString;
    }
    void test(){
        System.out.println(testString);
    }
}
```
