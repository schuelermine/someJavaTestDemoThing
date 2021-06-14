// Utility class for pairs of values
public class Pair<T1,T2> {
    public Pair(T1 x, T2 y) {
        this.value1 = x;
        this.value2 = y;
    }
    private T1 value1;
    public T1 getValue1() {
        return this.value1;
    }
    private T2 value2;
    public T2 getValue2() {
        return this.value2;
    }
}
