package StringExamples;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class StringComparision {
    String s1 = "Java String Example!";
    String s2 = "String Example aaaaaaaaaaaaaa!";
    String s3 = "";
    String s4 = "Examples can be helpFUL";
    String s5 = s4;

    char[] array = new char[50];

    public void equalsMethod() {
        if(s5.equals(s4))
            System.out.println("s5.equals(s4) is valid : "+ s5.equals(s4));
        else
            System.out.println("s5.equals(s4) is not valid : "+ s5.equals(s4));

        if(s1.substring(0,3).equals(s5)) {
            System.out.println("s1.substring(0,3).equals(s5) is true");
        }
        if(s4.equals("Java")) {
            System.out.println("s4.equals(\"Java\")");
        }

        if(s5.equals("Java")) {
            System.out.println("s5.equals(\"Java\")");
        }

        if(s4.equalsIgnoreCase("java")) {
            System.out.println("s4.equalsIgnoreCase(\"java\")");
        }

        if(s5.equalsIgnoreCase("java")) {
            System.out.println("s5.equalsIgnoreCase(\"java\")");
        }

        System.out.println("s1.charAt(0): "+s1.charAt(0));
    }

    public void doubleEqualsMEthod() {
        if(s4==s5) {
            System.out.println("s4==s5");
        } else
            System.out.println("s4!=s5");

        if(s4=="Java") System.out.println("s4==\"Java\"");
        else System.out.println("s4!=Java");

    }

    public void compareToMethod() {
        System.out.println("s1.compareTo(s2)= "+s1.compareTo(s2));
        System.out.println("s2.compareTo(s1)= "+s2.compareTo(s1));
        System.out.println("s1.compareTo(s1)= "+s1.compareTo(s1));
        System.out.println("s4.compareTo(s5)= "+s4.compareTo(s5));
        System.out.println("s5.compareTo(s3)= "+s5.compareTo(s3));

    }

    public void miscellaneousExamples() {
        System.out.println("s1.contains(\"!\"): "+s1.contains("!"));
        System.out.println("s1.endsWith(\"!\"): "+s1.endsWith("!"));
        System.out.println("String.format(s2, s3): "+String.format(s2, s3));
        System.out.println("s5.getBytes(): "+s5.getBytes());
        System.out.println("s5.getBytes(): "+s5.getBytes());
        System.out.println("s1.indexOf(0): "+s1.indexOf("!"));
        System.out.println("s1.lastIndexOf(s1): " + s1.lastIndexOf(s4));
    }

    public void stringFormatExample() {
        LocalDate localDateTime = LocalDate.now();
        Date date = java.sql.Date.valueOf(localDateTime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("DD-MM-YYYY");

        System.out.println(" "+simpleDateFormat.format( date));

        String s1 = String.format("%s", "Java Programming");
        String s2 = String.format("%d", 9);

        System.out.println(" "+ s1 +s2);
    }

    public void getCharsExample() {
        s1.getChars(0, 10, array, 1);
        System.out.println(array);
    }

    public void internExample() {
        String intern = s1.intern();
        String internValue = intern.substring(0,9);
        System.out.println(internValue);
        System.out.println("intern.equals(internValue): "+ intern.equals(internValue));
    }

    public void checkIfEmpty() {
        List<String> randomString = Arrays.asList();
        boolean empty = randomString.isEmpty();
        System.out.println(empty);
    }

    public void joinNSplit() {
        System.out.println(s1.join(s2));
        System.out.println(Arrays.toString(s1.split("\\[a-z A-Z\\]")));
    }

    public void replaceAndReplaceAll() {
        System.out.println(s1.replace("Java", "SpringBoot"));
        System.out.println(s2.replaceAll("a", "A"));
    }

    public void lowerNUpperCase() {
        System.out.println(s4.toUpperCase(Locale.KOREAN));
        System.out.println(s5.toLowerCase(Locale.FRENCH));
    }

    public void valueOfExample() {
        boolean bol = true;
        boolean bol2 = false;
        String c = "true";
        String d = "false";
        String a = String.valueOf(bol);
        String b = String.valueOf(bol2);
        System.out.println(a);
        System.out.println(b);
        System.out.println("a.equals(c): "+ a.equals(c));
        System.out.println("a.equals(c): "+ b.equals(d));
    }

    public static void main(String[] args) {
        StringComparision stringComparision = new StringComparision();
        stringComparision.compareToMethod();
        stringComparision.equalsMethod();
        stringComparision.doubleEqualsMEthod();
        stringComparision.getCharsExample();
        stringComparision.miscellaneousExamples();
        stringComparision.stringFormatExample();
        stringComparision.internExample();
        stringComparision.checkIfEmpty();
        stringComparision.joinNSplit();
        stringComparision.replaceAndReplaceAll();
        stringComparision.lowerNUpperCase();
        stringComparision.valueOfExample();
    }
}
