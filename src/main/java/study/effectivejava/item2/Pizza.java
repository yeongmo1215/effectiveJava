package study.effectivejava.item2;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public abstract class Pizza {
    public enum Topping {HAM, MUSHROOM, ONION, PEPPER, SAUSAGE}

    final Set<Topping> toppings;

    abstract static class Builder<T extends Builder<T>> {
        private final EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);

        public T addTopping(Topping topping) {
            this.toppings.add(Objects.requireNonNull(topping));
            return self();
        }

        abstract Pizza build();

        abstract T self();
    }

    Pizza(Builder builder) {
        this.toppings = builder.toppings.clone();
    }

    public void print() {
        System.out.println(this.toppings);
    }

}
