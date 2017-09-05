package designPattern.pipeline;

import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * @author: miaoxing
 * DATE:    2017/9/4
 */
public class Pipeline {

    static abstract class BusinessHandler<T> {
        BusinessHandler<T> next;

        public void setNext(BusinessHandler<T> next) {
            this.next = next;
        }

        public T handle(T input) {
            T t = work(input);

            if (next != null) {
                next.handle(t);
            }

            return t;
        }

        abstract T work(T input);
    }

    public static class OrderHandler extends BusinessHandler<String> {
        @Override
        String work(String input) {
            return input + " -> 处理订单";
        }
    }

    public static class LogisticsHandler extends BusinessHandler<String> {
        @Override
        String work(String input) {
            return input + " -> 准备物流";
        }
    }

    public static String testOrder() {
        BusinessHandler<String> order = new OrderHandler();
        BusinessHandler<String> logistics = new LogisticsHandler();
        order.setNext(logistics);
        return order.handle("订单数据");
    }

    public static String testOrderByLambda() {
        UnaryOperator<String> order = input -> input + " -> 处理订单";
        UnaryOperator<String> logistics = input -> input + " -> 处理订单";
        Function<String, String> pipeline = order.andThen(logistics);
        return pipeline.apply("订单数据");
    }

}
