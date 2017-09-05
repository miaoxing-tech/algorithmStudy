package palindrome;

/**
 * @author: miaoxing
 * DATE:    2017/9/4
 */
public class PalindromeNumber {

    public static boolean isPalindrome(int x) {
        if (x == 0) {
            return true;
        }

        if (x < 0 || x % 10 == 0) {
            return false;
        }

        int reverse = 0;
        while (reverse < x) {
            reverse = reverse * 10 + x % 10;
            if (reverse == x || reverse == (x = x / 10)) {
                return true;
            }
        }

        return false;
    }

    public static boolean isPalindrome2(int x) {
        if (x == 0) {
            return true;
        }

        if (x < 0 || x % 10 == 0) {
            return false;
        }

        int reverse = 0;
        while (reverse < x) {
            reverse = reverse * 10 + x % 10;
            x = x / 10;
        }

        return reverse == x || reverse / 10 == x;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(10));
        System.out.println(isPalindrome(12321));
        System.out.println(isPalindrome(1221));
        System.out.println(isPalindrome(121));
    }

}
