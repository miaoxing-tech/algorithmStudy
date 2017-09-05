package collection;

import stream.Stream;

/**
 * @author: miaoxing
 * DATE:    2017/8/18
 */
public interface MxCollection<E> extends Iterable<E> {

    int size();

    boolean isEmpty();

    boolean contains(E e);

    boolean add(E e);

    boolean remove(E e);

    default Stream<E> stream() {
        return null;
    }

}
