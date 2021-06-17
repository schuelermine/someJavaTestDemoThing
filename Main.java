import java.math.BigInteger;

// Yes, this code is inefficient.
// Yes, BigInt should not be used.
// Yes, this is completely unnecessary.
// I did this for fun :)

public class Main {
    public static void main(String[] args) {
        BigInteger targetSize;
        BigInteger sampleSize;
        boolean useRecursion;
        try {
            targetSize = new BigInteger(args[0]);
            sampleSize = new BigInteger(args[1]);
        } catch (IllegalArgumentException|ArrayIndexOutOfBoundsException $) {
            throw new IllegalArgumentException("\nSome parameters were incorrect.\nSyntax: {executable or java call} {list length} {snippet length}");
        }
        try {
            useRecursion = args[2].equals("R");
        } catch (ArrayIndexOutOfBoundsException $) {
            useRecursion = false;
        }
        System.out.println("Called with arguments:");
        for (String string : args) {
            System.out.println(string);
        }
        if (useRecursion) {
            System.out.println("Using recursive implementations");
        }
        System.out.println("Generating list…");
        LList<BigInteger> list = new LList<>();
        for (var i = BigInteger.valueOf(0); i.compareTo(targetSize) < 0; i = i.add(BigInteger.valueOf(1))) {
            list = new LList<>(i, list);
        }
        System.out.println("Resulting list is of size:");
        System.out.println(list.getBigLength().toString(10));
        System.out.println("Printing requested " + sampleSize.toString(10) + " elements");
        for (var i = BigInteger.valueOf(0); i.compareTo(sampleSize.min(targetSize)) < 0; i = i.add(BigInteger.valueOf(1))) {
            System.out.println
                ((useRecursion ? list.getElementAtBigRecursive(i) : list.getElementAtBig(i)).toString(10));
        }
    }
}
