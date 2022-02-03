# ITEM 10 equals 는 일반규약을 지켜 재정의하라

equals 관련 문제를 회피하는 가장 쉬운 길은 아예 재정의 하지 않는것이다.

아래 상황중 하나에 해당한다면 재정의 하지 않는 것이 최선이다.
* 각 인스턴스가 본질적으로 고유하다.(값을 표현하는게 아니라 동작하는 개체를 포현하는 클래스 ex. Thread)
* 인스턴스의 '논리적 동치성(logical equality)'를 검사할 일이 없다.(ex. Pattern)
* 상위 클래스에서 재정의한 equals가 하위 클래스에도 딱 들어 맞는다.(List 구현체들은 AbstractList로부터, Map 구현체들은 AbstractMap으로부터 상속받아 그대로 쓴다.)
* 클래스가 private이거나 pachage-private이고 equals 메서드를 호출할 일이 없다.

equals가 실수라도 호출되는걸 막는방법
```java
@Override
public boolean equals(Object o){
    throw new AssertionError(); //호출금지
        }
```

equals를 재정의 해야하는 때
* 객체 식별성이 아니라 논리적 동치성을 확인해야 하는데 상위 클래스의 equals가 논리적 동치성을 비교하도록 재정의 되지 않았을때(ex. Integer, String, ..)
  * 재정의 할 경우 Map의 키와 Set의 원소로 사용 가능
* 값 클래스라 하더라도 같은 인스턴스가 둘 이상 만들어지지 않음을 보장하는 인스턴스 통제 클래스라면 equalse를 재정의 하지 않아도된다.(ex. Enum)
  * 이 경우 Object의 equals가 논리적 동치성까지 확인해준다고 볼 수 있다.


equals 메서드는 동치관계(equivalence relation)를 구현하며, 다음을 만족한다.
* 반사성(reflecivity) : null이 아닌 모든 참조값 x에 대해, x.equals(x)는 true 다
* 대칭성(symmetry) : null이 아닌 모든 참조값 x, y에 대해, x.equals(y)가 true면 y.equals(x)도 true다
* 추이성(transivity) : null이 아닌 모든 참조값 x, y, z에 대해, x.equals(y)가 true이고 y.equals(z)도 true이면, x.equals(z)도 true 이다.
* 일관성(consistency) : null이 아닌 모든 참조값 x, y에 대해, x.equals(y)를 반복해서 호출하면 항상 true를 반환하거나 false를 반환한다.
* null-아님 : null이 아닌 모든 참조값 x에 대해 x.equals(null)은 false다.

구체클래스를 확장해 새로운 값을 추가하면서 equals 규약을 만족시킬 방법은 존재하지 않는다.(instanceof 대신 getClass()로 검사시에는 리스코프 치환원칙 위배)
이 경우 상속 대신 컴포지션을 사용.
구체클래스를 상속하는 대신 private 필드로 두고 같은 위치의 일반 구체인스턴스를 반환하는 뷰(View)메서드를 public으로 추가하는 식이다.
* 상위 클래스를 직접 인스턴스로 만드는게 불가능 하다면 지금까지 위의 문제는 일어나지 않는다.(ex. 추상 클래스)
```java
import java.awt.*;
import java.util.Objects;

public class ColorPoint {
    private final Point point;
    private final Color color;

    public ColorPoint(int x, int y, Color color) {
        this.point = new Point(x, y);
        this.color = Objects.requireNonNull(color);
    }
    
    public Point asPoint(){
        return this.point;
    }
    
    @Override
    public boolean equals(Object o){
        if(!(o instanceof ColorPoint))
            return false;
        
        ColorPoint cp = (ColorPoint)o;
        return cp.point.equals(this.point) && cp.color.equals(this.color);
    }
}
```

instanceof는 객체가 null인 경우 Exception을 발생하지 않고 false 반환.


equal 메서드 구현방법
1. == 연산자를 사용해 입력이 자기 자신의 참조인지 확인.
2. instanceof 연산자로 입력이 올바른 타입인지 확인
3. 입력을 올바른 타입으로 형변환(2. instanceof연산자로 검사하였으면 100% 통과)
4. 입력 객체와 자기 자신의 대응되는 '핵심'필드들이 모두 일치하는지 하나씩 검사


float, double을 제외한 기본 타입 필드는 == 연산자로 비교하고, 참조 타입 필드는 각각의 equals 메서드로, float와 double 필드는 각각 정적 메서드인 Float.compare(float, float)와 Double.compare(double, double)로 비교한다
Float.NaN, -0.0f 특수한 부동소수값을 다루기 때문!
null도 정상값으로 취급하는 참조 타입 필드의 경우 Object.equals(object, object)로 비교해 NullPointException 발생을 예방하자


* equals를 재정의 할땐 hashCode도 반드시 재정의하자
* 너무 복잡하게 구현하지 말자(필드들의 동치성만 검사해도 equals 규약을 어렵지 않게 지킬 수 있다)
* Object 외의 타입을 매개변수로 받는 equals 메서드는 선언하지 말자

equals 테스트를 대신해주는 오픈소스 : 구글이 만든 AutoValue 프레임워크

꼭 필요한 경우가 아니면 equals 메서드를 재정의하지말자
