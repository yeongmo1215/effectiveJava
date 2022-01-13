package study.effectivejava.item2;

public class Item2Application {
    public static void main(String[] args) {

        NyPizza.Builder nyBuilder = new NyPizza.Builder(NyPizza.Size.LARGE);

        NyPizza nyPizza = nyBuilder.addTopping(Pizza.Topping.HAM).addTopping(Pizza.Topping.SAUSAGE).build();
        CalzonePizza calzonePizza = new CalzonePizza.Builder().addTopping(Pizza.Topping.MUSHROOM).addTopping(Pizza.Topping.PEPPER).sourceInside().build();

        // clone Test
        calzonePizza.print();
        nyPizza.print();
        nyBuilder.addTopping(Pizza.Topping.ONION).build().print();
        nyPizza.print();

    }
}
