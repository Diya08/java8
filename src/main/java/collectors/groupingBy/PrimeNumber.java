package collectors.groupingBy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PrimeNumber {

    public List<Integer> retrievePrimeNumbersFromList() {
       List<Integer> numbers = Arrays.asList(7,8,9,11,16,99,37,0,1,2,3,4,5,6);

        return numbers.parallelStream()
                .sorted()
                .filter(number -> IntStream.range(2, number).noneMatch(value -> number%value==0 ))
                .collect(Collectors.toList());

        /*return numbers.stream()
                .sorted()
                .filter(number -> IntStream.range(2, number).noneMatch(value -> number%value==0 ))
                .collect(Collectors.toList());*/

        //return IntStream.range(2, num/2).noneMatch(value -> num%value==0 );
    }

    public static void main(String... args) {
        PrimeNumber primeNumber = new PrimeNumber();
        System.out.println(primeNumber.retrievePrimeNumbersFromList());

    }
}
