package sort;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * @author: miaoxing
 * DATE:    2017/8/30
 */
public class MergeSort {

    public static void main(String[] args) {
        List<Integer> test = Lists.newArrayList(5, 5, 5, 1, 4, 6, 5, 5, 5, 5, 12, 435, 5, 21, 5, 12, 545, 5, 213, 43512, 2, 5, 8, 9, 0, 5);
        MergeSort.sort(test);
        System.out.println(test);
    }

    public static void sort(List<Integer> source) {
        if (CollectionUtils.isEmpty(source)) {
            throw new IllegalArgumentException("传入参数有误");
        }

        sort(source, 0, source.size() - 1);
    }

    private static void sort(List<Integer> source, int start, int end) {
        if (start >= end) {
            return;
        }

        int middle = (start + end) / 2;

        // 1.左侧排序
        sort(source, start, middle);

        // 2.右侧排序
        sort(source, middle + 1, end);

        // 3.左右归并处理
        merge(source, start, middle, end);
    }

    private static void merge(List<Integer> source, int start, int middle, int end) {
        List<Integer> tmp = Lists.newArrayListWithCapacity(end - start + 1);

        int indexLeft = start;
        int indexRight = middle + 1;

        while (indexLeft <= middle && indexRight <= end) {
            if (source.get(indexLeft) < source.get(indexRight)) {
                tmp.add(source.get(indexLeft));
                indexLeft++;
            } else {
                tmp.add(source.get(indexRight));
                indexRight++;
            }
        }

        while (indexLeft <= middle) {
            tmp.add(source.get(indexLeft));
            indexLeft++;
        }

        while (indexRight <= end) {
            tmp.add(source.get(indexRight));
            indexRight++;
        }

        for (int index = 0; index < end - start + 1; index++) {
            source.set(start + index, tmp.get(index));
        }
    }

}
