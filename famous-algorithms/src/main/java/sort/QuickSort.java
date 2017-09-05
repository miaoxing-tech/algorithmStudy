package sort;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * @author: miaoxing
 * DATE:    2017/8/30
 */
public class QuickSort {

    public static void main(String[] args) {
        List<Integer> test = Lists.newArrayList(5, 5, 5, 1, 4, 6, 5, 5, 5, 5, 12, 435, 5, 21, 5, 12, 545, 5, 213, 43512, 2, 5, 8, 9, 0, 5);
        QuickSort.sort(test);
        System.out.println(test);
    }

    public static void sort(List<Integer> source) {
        if (CollectionUtils.isEmpty(source)) {
            throw new IllegalArgumentException("传入参数有误");
        }

        sort(source, 0, source.size() - 1);
    }

    private static void sort(List<Integer> source, int start, int end) {
        if (end - start <= 0) {
            return;
        }

        Integer middle = partition(source, start, end);
        sort(source, start, middle - 1);
        sort(source, middle + 1, end);
    }

    private static Integer partition(List<Integer> source, int start, int end) {
        Integer indexStart = start;
        Integer indexEnd = end;

        indexStart++;
        while (indexStart < indexEnd) {
            while (source.get(indexStart) <= source.get(start) && indexStart < indexEnd) {
                indexStart++;
            }

            while (source.get(indexEnd) >= source.get(start) && indexStart < indexEnd) {
                indexEnd--;
            }

            if (indexStart < indexEnd) {
                Integer tmp = source.get(indexStart);
                source.set(indexStart, source.get(indexEnd));
                source.set(indexEnd, tmp);
            }
        }

        if (source.get(indexStart) > source.get(start)) {
            indexStart--;
        }

        Integer tmp = source.get(start);
        source.set(start, source.get(indexStart));
        source.set(indexStart, tmp);

        return indexStart;
    }

//    private static Integer partition(List<Integer> source, int start, int end) {
//        Integer key = source.get(start);
//        while (start < end) {
//            while (source.get(end) >= key && start < end) {
//                end--;
//            }
//            source.set(start, source.get(end));
//
//            while (source.get(start) <= key && start < end) {
//                start++;
//            }
//            source.set(end, source.get(start));
//        }
//
//        source.set(start, key);
//        return start;
//    }


}
