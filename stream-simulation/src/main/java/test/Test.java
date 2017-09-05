package test;

import collection.MxArrayList;
import collection.MxList;

/**
 * @author: miaoxing
 * DATE:    2017/8/18
 */
public class Test {

    public static void main(String[] args) {
        MxList<String> list = MxArrayList.newMxArrayList("123", "sadaf", "22", "qqqqqqqqqqqqqq");
        for (String a : list) {
            a.length();
        }
    }

}
