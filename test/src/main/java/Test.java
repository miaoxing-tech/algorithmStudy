import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author: miaoxing
 * DATE:    2017/7/20
 */
public class Test {

    public static void main(String[] args) {
        Test p12 = new Test();
//        p12.printToMaxOfNDigits(2);
//        p12.permutation("abcdd");

//        System.out.println(p12.getUglyNumber(12));
        System.out.println(11 / 4);
        int a = 1;
        java.util.function.Function<Integer, Integer> function = Test::getUglyNumber;
        java.util.function.Function<Integer, Integer> function2 = i -> Test.getUglyNumber(i);
        Integer b = function.apply(a);
        Consumer<Integer> consumer = p12::printToMaxOfNDigits;
    }

    public static int getUglyNumber(int n) {
        if (n < 0) return 0;
        int[] uglyArray = new int[n];
        uglyArray[0] = 1;
        int multiply2 = 1;
        int multiply3 = 1;
        int multiply5 = 1;
        for (int i = 1; i < uglyArray.length; i++) {
            int min = min(uglyArray[multiply2 - 1] * 2, uglyArray[multiply3 - 1] * 3, uglyArray[multiply5 - 1] * 5);
            uglyArray[i] = min;
            while (uglyArray[multiply2 - 1] * 2 <= min)
                multiply2++;
            while (uglyArray[multiply3 - 1] * 3 <= min)
                multiply3++;
            while (uglyArray[multiply5 - 1] * 5 <= min)
                multiply5++;
        }
        return uglyArray[n - 1];
    }

    private static int min(int i, int j, int k) {
        int min = (i < j) ? i : j;
        return (min < k) ? min : k;
    }

    public void printToMaxOfNDigits(int n) {
        int[] array = new int[n];
        if (n <= 0)
            return;
        printArray(array, 0);
    }

    private void printArray(int[] array, int n) {
        for (int i = 0; i < 10; i++) {
            if (n != array.length) {
                array[n] = i;
                printArray(array, n + 1);
            } else {
                boolean isFirstNo0 = false;
                for (int anArray : array) {
                    if (anArray != 0) {
                        System.out.print(anArray);
                        if (!isFirstNo0) isFirstNo0 = true;
                    } else {
                        if (isFirstNo0)
                            System.out.print(anArray);
                    }
                }
                System.out.println();
                return;
            }
        }
    }

    public void permutation(String str) {
        int count = 0;
        if (str == null)
            return;
        char[] chs = str.toCharArray();
        int point = 0;
        System.out.print(chs);
        System.out.print(" ");
        count++;
        char temp1 = chs[point];
        chs[point] = chs[++point];
        chs[point] = temp1;
        while (!String.valueOf(chs).equals(str)) {
            System.out.print(chs);
            System.out.print(" ");
            count++;
            if (point == chs.length - 1) {
                char temp = chs[point];
                chs[point] = chs[0];
                chs[0] = temp;
                point = 0;
            } else {
                char temp = chs[point];
                chs[point] = chs[++point];
                chs[point] = temp;
            }
        }
        System.out.println(count);
    }

    private List<Integer> test1() {
        List<Integer> incomesFrom = Lists.newArrayList(5100, 6300, 7500, 5252);
        List<Integer> incomesTo = Lists.newArrayListWithCapacity(incomesFrom.size());
        for (Integer income : incomesFrom) {
            incomesTo.add(income * 2);
        }
        return incomesTo;
    }

    private List<Integer> test2() {
        List<Integer> incomesFrom = Lists.newArrayList(5100, 6300, 7500, 5252);
        return Lists.transform(incomesFrom, income -> income * 2);
    }

    private void test3() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                daSth1();
                daSth2();
            }
        }).start();

        new Thread(() -> {
            daSth1();
            daSth2();
        }).start();

        new Thread(this::daSth1).start();

        new Thread(() -> daSth1()).start();

        List<Integer> integerList = Lists.newArrayList(1, 2, 4, 3, null);
        Collections.sort(integerList, new Comparator<Integer>() {
            @Override
            public int compare(Integer x, Integer y) {
                if (x == null)
                    return -1;
                if (y == null)
                    return 1;
                return x - y;
            }
        });

        Collections.sort(integerList, (x, y) -> {
            if (x == null)
                return -1;
            if (y == null)
                return 1;
            return x - y;
        });


        List<Person> persons = Lists.newArrayList();
        Comparator<Person> comparator1 = Comparator.comparing(new Function<Person, String>() {
            @Override
            public String apply(Person person) {
                return person.getName();
            }
        });
        Collections.sort(persons, comparator1);

        Comparator<Person> comparator2 = Comparator.comparing(p -> p.getName());
        Collections.sort(persons, comparator2);

        Comparator<Person> comparator3 = Comparator.comparing(Person::getName);
        Collections.sort(persons, comparator3);
    }

    class Person {
        private final String name;
        private final int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public int getAge() {
            return age;
        }

        public String getName() {
            return name;
        }
    }


    private void daSth1() {

    }

    private void daSth2() {

    }

}
