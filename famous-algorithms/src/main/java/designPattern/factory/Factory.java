package designPattern.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author: miaoxing
 * DATE:    2017/9/4
 */
public class Factory {

    public static Fruit createFruit(String name) {
        switch (name) {
            case "Apple":
                return new Apple();
            case "Banana":
                return new Banana();
            case "Pear":
                return new Pear();
            default:
                throw new RuntimeException("No Such Fruit!");
        }
    }

    final static Map<String, Supplier<Fruit>> cache = new HashMap<>();

    static {
        cache.put("Apple", Apple::new);
        cache.put("Banana", Banana::new);
        cache.put("Pear", Pear::new);
    }

    public Fruit createFruitLambda(String name) {
        Supplier<Fruit> supplier = cache.get(name);
        if (supplier != null) {
            return supplier.get();
        }

        throw new RuntimeException("No Such Fruit!");
    }

}
