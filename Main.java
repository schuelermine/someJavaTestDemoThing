import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        System.out.println("Called with arguments:");
        for (String string : args) {
            System.out.println(string);
        }
        BigInteger max = new BigInteger(args[0]);
        BigInteger max2 = new BigInteger(args[1]);
        System.out.println("Generating list…");
        LList<BigInteger> list = new LList<>();
        for (var i = BigInteger.valueOf(0); i.compareTo(max) < 0; i = i.add(BigInteger.valueOf(1))) {
            list = new LList<>(i, list);
        }
        System.out.print("Resulting list is of size:\t\t");
        System.out.println(list.getLength().toString(10));
        System.out.println("Printing requested " + max2.toString(10) + " elements");
        for (var i = BigInteger.valueOf(0); i.compareTo(max2.min(max)) < 0; i = i.add(BigInteger.valueOf(1))) {
            System.out.println(i.toString(10));
        }
    }
}
