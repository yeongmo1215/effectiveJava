package study.effectivejava.item2;

public class CalzonePizza extends Pizza {

    private final boolean sourceInside;

    public static class Builder extends Pizza.Builder<Builder> {

        private boolean sourceInside = false;

        public Builder sourceInside() {
            this.sourceInside = true;
            return this;
        }

        @Override
        CalzonePizza build() {
            return new CalzonePizza(this);
        }

        @Override
        Builder self() {
            return this;
        }
    }

    private CalzonePizza(Builder builder) {
        super(builder);
        this.sourceInside = builder.sourceInside;
    }
}
