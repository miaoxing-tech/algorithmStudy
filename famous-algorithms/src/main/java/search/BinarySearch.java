package search;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author: miaoxing
 * DATE:    2017/8/30
 */
public class BinarySearch {

    public static void main(String[] args) {
        List<Integer> test = Lists.newArrayList(5, 5, 5, 1, 4, 6, 5, 5, 5, 5, 12, 435, 5, 21, 5, 12, 545, 5, 213, 43512, 2, 5, 8, 9, 0, 5);
        System.out.println(BinarySearch.search(test, 5));
    }

    public static boolean search(List<Integer> source, Integer target) {
        Collections.sort(source);

        if (CollectionUtils.isEmpty(source) || target == null) {
            throw new IllegalArgumentException("传入的参数不合法");
        }

        int start = 0;
        int end = source.size();
        int middle;

        while (start <= end) {
            middle = (start + end) / 2;
            if (Objects.equals(target, source.get(middle))) {
                return true;
            } else if (target < source.get(middle)) {
                end = middle - 1;
            } else {
                start = middle + 1;
            }
        }
        return false;
    }

}
