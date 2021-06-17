import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        BigInteger targetSize;
        BigInteger sampleSize;
        try {
            targetSize = new BigInteger(args[0]);
            sampleSize = new BigInteger(args[1]);
        } catch (Exception e) {
            throw new IllegalArgumentException("\nSome parameters were incorrect.\nSyntax: {executable or java call} {list length} {snippet length}");
        }
        System.out.println("Called with arguments:");
        for (String string : args) {
            System.out.println(string);
        }
        System.out.println("Generating list…");
        LList<BigInteger> list = new LList<>();
        for (var i = BigInteger.valueOf(0); i.compareTo(targetSize) < 0; i = i.add(BigInteger.valueOf(1))) {
            list = new LList<>(i, list);
        }
        System.out.print("Resulting list is of size:\t\t");
        System.out.println(list.getBigLength().toString(10));
        System.out.println("Printing requested " + sampleSize.toString(10) + " elements");
        for (var i = BigInteger.valueOf(0); i.compareTo(sampleSize.min(targetSize)) < 0; i = i.add(BigInteger.valueOf(1))) {
            System.out.println(list.getElementAtBig(i).toString(10));
        }
    }
}
