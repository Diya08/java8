package collectors.groupingBy;

import java.util.HashMap;
import java.util.Iterator;

public final class ImmutableClassExample {
    // class - final and fields - private and final
    private final String name;
    private final int id;
    private HashMap<String, String> employeeDetails;

    // only getters
    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    // clone and return instead of returning actual reference
    public HashMap<String, String> getEmployeeDetails() {
        //return employeeDetails;
        return (HashMap<String, String>) this.employeeDetails.clone();
    }

    // intialize all variables in constructor through deep copy
    public ImmutableClassExample(String name, int id, HashMap<String, String> details) {
        this.name = name;
        this.id = id;
        HashMap<String, String> temporaryMap = new HashMap<>();

        String key;

        Iterator<String> iterator = details.keySet().iterator();

        while(iterator.hasNext()) {
            key = iterator.next();
            temporaryMap.put(key, details.get(key));
        }

        this.employeeDetails = temporaryMap;

    }

    // shallow copy constructor
   /* public collectors.groupingBy.ImmutableClassExample(String name, int id, HashMap<String, String> details) {
        this.name = name;
        this.id = id;
        this.employeeDetails = details;
    }*/

    public static void main(String... args) {
        HashMap<String, String> sampleHash = new HashMap<>();

        sampleHash.put("1", "OIB");
        sampleHash.put("2", "DIGIT");
        sampleHash.put("3", "EVERIS");

        String name = "Smitha";
        int id = 2275020;

        ImmutableClassExample immutableClassExample = new ImmutableClassExample(name, id, sampleHash);

        // check for clone values
        System.out.println(name == immutableClassExample.getName());
        System.out.println(id == immutableClassExample.getId());
        System.out.println(sampleHash == immutableClassExample.getEmployeeDetails());

        // values of immutableClassExample
        System.out.println(immutableClassExample.getEmployeeDetails());
        System.out.println(immutableClassExample.getId());
        System.out.println(immutableClassExample.getName());

        //change the values of the variables
        name = "Sameera";
        id = 2275010;
        sampleHash.put("4", "I am not accepted");
        System.out.println(immutableClassExample.getEmployeeDetails());
        System.out.println(immutableClassExample.getId());
        System.out.println(immutableClassExample.getName());

        HashMap<String, String> testMap = immutableClassExample.getEmployeeDetails();
        testMap.put("5", "I dont exist");
        System.out.println(immutableClassExample.getEmployeeDetails());
    }
}
