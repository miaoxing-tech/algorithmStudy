import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: miaoxing
 * DATE:    2017/7/24
 */
public class MyersDiff {

    private static final String PLUS_WITH_BLANK = "+ ";
    private static final String MINUS_WITH_BLANK = "- ";

    public static List<String> diffTwoListString(List<String> from, List<String> to) {
        if (CollectionUtils.isEmpty(from)) {
            return to.stream().map(s -> PLUS_WITH_BLANK + s).collect(Collectors.toList());
        }

        if (CollectionUtils.isEmpty(to)) {
            return from.stream().map(s -> MINUS_WITH_BLANK + s).collect(Collectors.toList());
        }


        return null;
    }

    public static void main(String[] args) {
        List<String> a = Lists.newArrayList("A", "B");
        System.out.println(a);

        LocalDate beginDate = LocalDate.of(2016, 5, 30);
        LocalDate endDate = LocalDate.of(2017, 8, 3);
        long days = endDate.toEpochDay() - beginDate.toEpochDay();
        System.out.println(days);
    }


    public void test(int a) {
        switch (a) {
            case 1:
                a++;
                return;
            case 2:
                a++;
                break;
        }

        a++;
    }

}
