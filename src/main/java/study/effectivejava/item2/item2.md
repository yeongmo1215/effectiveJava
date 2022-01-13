# ITEM 2 생성자에 매개변수가 많다면 빌더를 고려하라

생성자 혹은 정적 팩터리 메서드 사용시 매개변수가 많을 경우 유연하게 대응하기 어려움

* 점층적 생성자 패턴(안정성 좋음)
    * 클라이언트 코드를 작성하거나 읽기 어려움
* 자바빈즈 패턴(가독성 좋음)
    * 객체 하나를 만들기 위해 어려 메서드 호출해야하고, 객체가 완전히 생성(여러 메서드 호출)되기 전까지는 일관성(consistency)이 무너진 상태에 놓이게 된다(ex. fromDate가 toDate보다
      미래인 상태존재)
    * 불변 클래스(immutable class)로 만들수 없음
* Freeze
    * 다루기 어려워서 실전에서는 거의 쓰지 않음.

## 빌더 패턴(builder pattern)

점층적 생성자 패턴의 안정성과 자바빈즈 패턴의 가독성을 모두 겸비 파이썬과 스칼라에 잇는 명명된 선택적 매개변수를 휸내낸 것 계층적으로 설계된 클래스와 함께 쓰기에 좋다

### 빌더 패턴 사용 방법

1. 필수 매개변수만으로 생성자(혹은 정적 팩터리)를 호출해 빌더 객체를 얻는다.
2. 빌더 객체가 제공하는 일종의 세터 메서드들로 원하는 선택 매개변수들을 설정한다.
3. 매개변수가 없응 ```build()``` 메서드를 호출해 최종적으로 얻고자 하는 객체를 얻는다.(build 메소드에서 불변식(일관성 유지 위한) 검사)

```java
public class NutritionFacts {
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    public static class Builder {
        //필수 매개변수
        private final int servingSize;
        private final int servings;

        //선택적 매개변수(기본값 초기화)
        private int calories = 0;
        private int fat = 0;
        private int sodium = 0;
        private int carbohydrate = 0;

        public builder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }

        public Builder calories(int calories) {
            this.calories = calories;
            return this;
        }

        public Builder fat(int fat) {
            this.fat = fat;
            return this;
        }

        public Builder sodium(int sodium) {
            this.sodium = sodium;
            return this;
        }

        public Builder carbohydrate(int carbohydrate) {
            this.carbohydrate = carbohydrate;
            return this;
        }

        public NutritionFacts build() {
            return new NutritionFacts(this);
        }
    }

    private NutritionFacts(Builder builder) {
        servingSize = builder.servingSize;
        servings = builder.servings;
        calories = builder.calories;
        fat = builder.fat;
        sodium = builder.sodium;
        carbohydrate = builder.carbohydrate;
    }

    public static void main(String[] args) {
        NutritionFacts nutritionFacts = new NutritionFacts.Builder(240, 8).calories(10).carbohydrate(100).sodium(20).fat(1).build();
    }
}
```

#시간이 지날수록 매개변수가 많아지는 경향이 있으므로 빌더로 시작하는 편이 나을때가 많다.