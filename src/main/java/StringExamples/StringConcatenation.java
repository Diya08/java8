package StringExamples;

public class StringConcatenation {
    String s1 = "Java String Example!";
    String s2 = "String Example!";
    String s3 = "";
    String s4 = "Java";
    String s5 = s4;

    public String plusOperator() {
        return s4+=s2;
    }

    public void concatMethod() {
        System.out.println(s1.concat(s2).concat(s3).concat(s4).concat(s5));
        char x = s1.charAt(0);
        System.out.println("(byte) x: "+((byte) x));
    }

    public static void main(String[] args) {
        StringConcatenation stc = new StringConcatenation();
        System.out.println(stc.plusOperator());
        stc.concatMethod();
        System.out.println(stc.plusOperator());
    }
}
