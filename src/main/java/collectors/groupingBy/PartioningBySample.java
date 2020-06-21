package collectors.groupingBy;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PartioningBySample {
    private List<Person> personList = Arrays.asList(
            new Person("Ram", 20),
            new Person("Shyam", 30),
            new Person("Mohan", 50),
            new Person("Ravi", 40),
            new Person("Amit", 10),
            new Person("Sumit", 30)
    );

    public Map<Boolean, List<Person>> partitionByAge() {
        return this.personList.stream().collect(Collectors.partitioningBy(person -> person.getAge() > 30));
    }

    public Map<Boolean, List<Person>> partitionByAgeAndReturnSum() {
        return this.personList.stream().
                collect(Collectors.partitioningBy(person -> person.getAge() > 30));
    }

    public static void main(String... args) {
        PartioningBySample partioning = new PartioningBySample();

        System.out.println("Return double age \n"+partioning.partitionByAge());
        System.out.println("Averaging int \n"+partioning.partitionByAgeAndReturnSum().
                get(true).stream().collect(Collectors.mapping(Person::getAge, Collectors.summingInt(Integer::intValue))));
        System.out.println("Averaging int \n"+partioning.partitionByAgeAndReturnSum().
                get(false).stream().collect(Collectors.mapping(Person::getAge, Collectors.summingInt(Integer::intValue))));
    }
}
