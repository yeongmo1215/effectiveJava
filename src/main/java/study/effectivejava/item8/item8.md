# ITEM 8 finalizer 와 cleaner 사용을 피하라

자바는 두가지 객체소멸자를 제공
* finalizer : 예측할 수 없고, 상황에 따라 위험할 수 있어 일반적으로는 불필요
* cleaner : finalizer 보다 덜 위함하지만, 여전히 예측할 수 없고, 느리고, 일반적으로 불필요

주의 : C++의 파괴자(destructor)와는 다른 개념
C++ : 특정 객체와 관련된 자원을 회수하는 보편적인 방법
JAVA : 접근할 수 없게 된 객체를 회수하는 역할은 가비지 컬렉터가 함.

finalizer 와 cleaner 는 즉시 수행된다는 보장이 없어서 제때 실행되어야 하는 작업은 절대 할 수 없음.
finalizer 와 cleaner 를 얼마나 신속히 수행하는지는 전적으로 가비지 컬렉터에 달렸음.
심지어 finalizer 와 cleaner 의 수행 여부조차 보장 할 수없음.
접근할 수 없는 일부 객체에 딸린 종료 작업을 전혀 수행하지 못한채 프로그램이 중단 될 수 있다.
상태를 영구적으로 수정하는 작업에서는 절대 finalizer 나 cleaner 에 의존해서는 안된다.
System.runFinalizersOnExit, Runtime.runFinalizersOnExit가 실행을 보장해주지만 심각한 결함때문에 수십년간 지탄받아왔다(Thread Stop)
Finalizer 에서 발생된 예외는 무시됨
심각한 성능문제 야기
Finalizer를 사용한 클래스는 finalizer 공격에 노출되어 심각한 보안 문제 야기
###finalizer 공격
생성자나 직렬화 과정에서 예외 발생시 이 생성되다만 객체에서 악의적인 하위 클래스의 finalizer가 수행될 수 있게 된다.
finalizer는 정적필드에 자신의 참조를 할당하여 가비지 컬렉터가 수집하지 못하게 막을 수도 있다.
객체생성을 막으려면 생성자에서 예외를 던지는 것만으로도 충분하지만, finalizer가 있다면 그렇지도 않다.

* final이 아닌 클래스를 finalizer 공격으로부터 방어하려면 아무 일도 하지않는 finalize 메서드를 만들고 final로 선언하자

 finalizer나 cleaner를 대신해줄 묘안은 AutoCloseable 구현(try-with-resources 사용) 
###AutoCloseable

 fianlizer나 cleaner가 존재하는 이유
 1. 자원의 소유자가 close 메서드를 호출하지 않는 것에 대비한 안전망 역할
 2. 네이티브 피어와 연결된 객체

cleaner(자바 8까지는 finalizer)는 안전망 혁할이나 중요하지 않은 네이티브 자원 회수용으로만 사용하자. 물론 이런 경우라도 불확실성과 성능 저하에 주의해야 한다.