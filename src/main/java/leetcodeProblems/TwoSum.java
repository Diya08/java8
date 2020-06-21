package leetcodeProblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoSum {
    List<Integer> numbers = Arrays.asList(2,5,7,9,7);
    int target = 9;

    public List<int[]> addNumbersToGetTarget() {
        List<int[]> integerList = new ArrayList<>();
       int i, j;
        int[] ints = {};
        for(i = 0; i <=numbers.size(); i++) {
           for(j = i+1; j < numbers.size()-i; j++) {
               int num = numbers.get(i);
               int nextNum = numbers.get(j);
               if(num + nextNum == target) {
                   ints = new int[]{i, j};
                   integerList.add(ints);
                   //integerList.addAll(ints);
               } else {
                   //System.out.println("Try again with some  other set of numbers");
               }
           }

       }

        return integerList;
    }

    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
        System.out.println(twoSum.addNumbersToGetTarget());
    }
}
