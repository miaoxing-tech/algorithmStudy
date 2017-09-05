package function.currying;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author: miaoxing
 * DATE:    2017/9/5
 */
public class Currying {

    public interface HandleFunction<T, R> {
        R handle(T... t);

        default HandleFunction<T, R> curry(T... t) {
            if (handle(t) == null) {
                return this;
            }
            return null;
        }
    }

    public static <T, R> HandleFunction<T, R> currying(Function<List<T>, R> fn) {
        List<T> result = Lists.newArrayList();
        return arg -> {
            if (arg != null && arg.length > 0) {
                result.addAll(Lists.newArrayList(arg));
            } else {
                return fn.apply(result);
            }
            return null;
        };
    }

    public static void main(String[] args) {
//        HandleFunction<Integer, Integer> curryPrice1 = Currying.currying(items ->
//                Long.valueOf(items.stream().mapToLong(Long::valueOf).sum()).intValue());
//
//
//        System.out.println(curryPrice1.curry(1).curry(3).curry(5).curry(7).handle());
//
//        // 或
//        HandleFunction<Integer, Integer> curryPrice2 = Currying.currying(items ->
//                Long.valueOf(items.stream().mapToLong(Long::valueOf).sum()).intValue());
//        curryPrice2.handle(1);
//        curryPrice2.handle(3);
//        curryPrice2.handle(5);
//        curryPrice2.handle(7);
//        System.out.println(curryPrice2.handle());

        Currying.test();
    }

    private static void test() {
        List<Integer> collection = Lists.newArrayList(1, -1, 2, 0, 5, -3);
        collection.removeIf(x -> x < 0);
        collection.forEach(x -> x = x * x);

        System.out.println(collection);

        List<Integer> list = Lists.newArrayList(1, -1, 2, 0, 5, -3);
        list.replaceAll(x -> x * x);
        list.sort((x, y) -> x - y);

        Map<String, String> map = Maps.newHashMap();
        map.forEach((k, v) -> System.out.println("key = " + k + ", value = " + v));

        String nextWrongMessage = "";
        map.compute("nextWrongMessage", (k, v) -> v == null ? nextWrongMessage : v.concat(nextWrongMessage));
        map.computeIfAbsent("Apple", x -> x == null ? null : "Apple: " + x);
        map.computeIfPresent("nextWrongMessage", (k, v) -> v == null ? nextWrongMessage : v.concat(nextWrongMessage));
    }

}
