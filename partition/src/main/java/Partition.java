import java.util.Arrays;

/**
 * @author: miaoxing
 * DATE:    2017/6/22
 */
public class Partition {

    public static int partitionInt(int[] source, long pivot) {
        int left = 0, right = source.length - 1;
        int leftPtr = left - 1, rightPtr = right + 1;

        while (true) {
            while (leftPtr < right && source[++leftPtr] < pivot) ;
            while (rightPtr > left && source[--rightPtr] > pivot) ;

            if (leftPtr >= rightPtr) {
                break;
            } else {
                swap(source, leftPtr, rightPtr);
            }
        }

        return leftPtr;
    }

    public static int partitionInt2(int[] source, int pivot) {
        int left = 0, right = source.length - 1;
        int leftPtr = left, rightPtr = right;

        while (true) {
            while (leftPtr < right && source[leftPtr] < pivot) {
                leftPtr++;
            }

            while (rightPtr > left && source[rightPtr] > pivot) {
                rightPtr--;
            }

            if (leftPtr >= rightPtr) {
                break;
            } else {
                swap(source, leftPtr, rightPtr);
            }
        }

        return leftPtr;
    }

    private static int partitionInt3(int[] source, int pivot, int left, int right) {
        while (true) {
            while (left < right && source[left] < pivot) {
                left++;
            }

            while (right > left && source[right] > pivot) {
                right--;
            }

            if (left >= right) {
                break;
            }

            swap(source, left, right);
        }

        return left;
    }

    private static void swap(int[] source, int leftPtr, int rightPtr) {
        int temp = source[rightPtr];
        source[rightPtr] = source[leftPtr];
        source[leftPtr] = temp;
    }

    private static void quickSort(int[] source, int left, int right) {
        if (left >= right) {
            return;
        }

        int pivot = source[right];
        int partition = partitionInt3(source, pivot, left, right);
        quickSort(source, left, partition - 1);
        quickSort(source, partition + 1, right);
    }

    public static void quickSort(int[] source) {
        if (source == null || source.length == 0) {
            return;
        }

        quickSort(source, 0, source.length - 1);
    }

    public static void main(String[] args) {
//        int[] test1 = {3, 2, 8, 5, 9, 4, 12, 7, 6};
//        int[] test2 = {3, 2, 8, 5, 9, 4, 12, 7, 6};
        int[] test3 = {3, 2, 8, 5, 9, 4, 12, 7, 6, 2, 6, 7};
//        partitionInt(test1, 6);
//        partitionInt2(test2, 6);
        quickSort(test3);
//        System.out.println(Arrays.toString(test1));
//        System.out.println(Arrays.toString(test2));
        System.out.println(Arrays.toString(test3));
    }

}
