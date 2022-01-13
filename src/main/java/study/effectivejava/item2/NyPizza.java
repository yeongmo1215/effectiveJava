package study.effectivejava.item2;

public class NyPizza extends Pizza {

    public enum Size {SMALL, MEDIUM, LARGE}

    private final Size size;

    public static class Builder extends Pizza.Builder<Builder> {

        private final Size size;

        public Builder(Size size) {
            this.size = size;
        }

        @Override
        NyPizza build() {
            return new NyPizza(this);
        }

        @Override
        Builder self() {
            return this;
        }
    }

    NyPizza(Builder builder) {
        super(builder);
        this.size = builder.size;
    }
}
