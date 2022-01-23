# ITEM 7 다 쓴 객체 참조를 해제하라

### Reachability
Java GC는 객체가 가비지인지 판별하기 위해 reachability라는 개념을 사용한다.
어떤 객체에 유효한 참조가 있으면 reachable, 없으면 unreachable로 구별하고 unreachable객체를 가비지로 간주해 GC를 수행한다.


### 강한 참조(Strong Reference)
객체를 참조하는 일반적인 인스턴스 변수
이 객체를 가리키는 강한 참조가 있는 객체는 GC 대상이 되지 앟음
java.lang.ref 패키지를 사용하지 않음.
```java
Integer i = Integer.valueOf(1);
```

### 약한 참조 (Weak Reference)
```java
WeakReference<Sample> wr = new WeakReference<Sample>(new Sample());
sample ex = wr.get();
ex = null;
```
wr 객체는 강한 참조
ex 객체는 약한 참조
```ex = null;``` 하는 순간 처음 생성한 sample 객체는 오직 WeakReference 내부에서만 참조되는 상태로 weakly reachable 객체라고 하며 GC 대상이 됨.
LRU 캐시와 같은 어플리케이션에서는 softly reachable 객체보다는 weakly reachable 객체가 유리하므.
내부 캐시를 구현하고자 할때는 WeakReference, WeakHashMap으로도 충분.

### WeakHashMap
일반적인 HashMap과 다르게 Key에 해당하는 객체가 더이상 존재하지 않는경우 Map에 element를 자동으로 제거(GC) 함.
```java
public class
```

### 부드러운 참조 (Soft Reference)
힙에 남아있는 메모리의 크기와 해당 객체의 사용 빈도에 따라 GC 여부 결정
WeakReference와 달리 GC가 동작할때마다 회수되지 않으며 자주 사용할 수록 오래 살아남음
힙 공간이 Soft Reachable 객체에 의해 일정 부분 점유되어 전체 메모리 사용량이 높아지고 GC가 더 자주 일어나며 GC에 걸리는 시간도 상대적으로 길어지므로 주의 필요.

### Phantom Reference
GC가 finalize 된 이후 작업을 어플리케이션이 수행하게 되고 메모리 회수는 지연됨.
사용 X



