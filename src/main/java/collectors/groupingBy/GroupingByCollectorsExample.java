package collectors.groupingBy;

import java.util.*;
import java.util.stream.Collectors;

public class GroupingByCollectorsExample {
    private List<Person> personList = Arrays.asList(
            new Person("Ram", 20),
            new Person("Shyam", 30),
            new Person("Mohan", 50),
            new Person("Ravi", 40),
            new Person("Amit", 40),
            new Person("Sumit", 30)
    );

    public Map<Integer, List<Person>> groupByAgeOfPerson() {
        return this.personList.stream().collect(Collectors.groupingBy(Person::getAge));
    }

    public Map<Integer, Long> groupByPersonCount() {
        return personList
                .stream()
                .collect(Collectors.groupingBy(Person::getAge, Collectors.counting()));
    }

    // mapping example
    public Optional<Integer> retrieveOlderPerson() {
        return personList
                .stream()
                .collect(
                        Collectors.mapping((Person p) -> p.getAge(), Collectors.maxBy(Integer::compareTo)));
    }

    public List<Person> orderByName() {
        return personList.stream().sorted(Comparator.comparing(Person::getName)).collect(Collectors.toList());
    }

    public List<Integer> doubleAgeAndReturnList() {
        return this.personList.stream().collect(Collectors.mapping(p -> p.getAge() * 2, Collectors.toList()));
    }

    public Double retrieveAverageOfAge() {
        return this.personList.stream().collect(Collectors.averagingInt(Person::getAge));
    }

    public static void main(String... args) {
        GroupingByCollectorsExample groupingByExample = new GroupingByCollectorsExample();

        System.out.println("Grouping based on age \n"+groupingByExample.groupByAgeOfPerson());
        System.out.println("Grouping based on age count \n"+groupingByExample.groupByPersonCount());
        System.out.println("Retrieve Senior collectors.groupingBy.Person \n"+groupingByExample.retrieveOlderPerson().get());
        System.out.println("Sorting by name \n"+groupingByExample.orderByName());

        // day2
        System.out.println("Return double age \n"+groupingByExample.doubleAgeAndReturnList());
        System.out.println("Averaging int \n"+groupingByExample.retrieveAverageOfAge());
    }
}
