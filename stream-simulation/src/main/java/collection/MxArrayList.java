package collection;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import java.util.Iterator;
import java.util.List;

/**
 * @author: miaoxing
 * DATE:    2017/8/18
 */
public class MxArrayList<E> implements MxList<E> {

    private List<E> LIST;

    private MxArrayList() {
        LIST = Lists.newArrayList();
    }

    @SafeVarargs
    private MxArrayList(E... elements) {
        Preconditions.checkNotNull(elements);
        LIST = Lists.newArrayList(elements);
    }

    public static <E> MxArrayList<E> newMxArrayList() {
        return new MxArrayList<>();
    }

    public static <E> MxArrayList<E> newMxArrayList(E... elements) {
        return new MxArrayList<>(elements);
    }

    @Override
    public int size() {
        return LIST.size();
    }

    @Override
    public boolean isEmpty() {
        return LIST.isEmpty();
    }

    @Override
    public boolean contains(E e) {
        return LIST.contains(e);
    }

    @Override
    public boolean add(E e) {
        return LIST.add(e);
    }

    @Override
    public boolean remove(E e) {
        return LIST.remove(e);
    }

    @Override
    public Iterator<E> iterator() {
        return LIST.iterator();
    }
}
