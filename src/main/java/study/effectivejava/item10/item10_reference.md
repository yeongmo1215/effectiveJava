# ITEM 10 equals 는 일반규약을 지켜 재정의하라

### 물리적 동치성(Object Identity)(=)
메모리에 저장된 변수가 가지는 값(=주소)이 서로 같은지 비교하는 것

### 논리적 동치성(locigal equality)(equals)
참조 타입(Reference Type) 변수(=주소에 해당되는 값)를 비교하는 것
```java
String s1 = "ten";
String s2 = new String("ten");
s1==s2;         // False(물리적 동치성)
s1.equals(s2);  // True (논리적 동치성)
```

### 리스코프 치환 원칙(Liskov substitution principle)
어떤 타입의 중요한 속성이라면 그 하위 타입에서도 마찬가지로 중요하다.

### AutoValue