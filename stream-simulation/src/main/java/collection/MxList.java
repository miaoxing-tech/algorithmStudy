package collection;

import java.util.Iterator;

/**
 * @author: miaoxing
 * DATE:    2017/8/18
 */
public interface MxList<E> extends MxCollection<E> {

    int size();

    boolean isEmpty();

    boolean contains(E e);

    boolean add(E e);

    boolean remove(E e);

    Iterator<E> iterator();

}
