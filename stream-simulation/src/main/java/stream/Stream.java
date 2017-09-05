package stream;

import java.util.function.Function;

/**
 * @author: miaoxing
 * DATE:    2017/8/18
 */
public interface Stream<A> {

    <B> Stream<B> map(Function<? super A, ? extends B> mapper);

}
