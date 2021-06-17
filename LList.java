import java.math.BigInteger;
import java.util.NoSuchElementException;

// Linked list class
// This structure allows null values
public class LList<T> {
    // #region Constructors
    // Creates an empty list
    public LList() {
        this.value = new Maybe<>();
    }
    // Creates a singleton list
    public LList(T head) {
        this.value = new Maybe<>(new Pair<>(head, new LList<>()));
    }
    // Creates a list from car/cdr
    public LList(T head, LList<T> tail) {
        this.value = new Maybe<>(new Pair<>(head, tail));
    }
    // #endregion

    // #region Accessors
    // Get value at index (with various types)
    public Maybe<T> maybeGetElementAtBig(BigInteger index) throws IllegalArgumentException {
        if (index.compareTo(LList.zero) < 0) {
            throw new IllegalArgumentException();
        }
        var tail = this;
        while (!tail.isEmpty()) {
            if (index.equals(LList.zero)) {
                return new Maybe<>(tail.getHead());
            }
            tail = tail.getTail();
            index = index.subtract(LList.one);
        }
        return new Maybe<>();
    }
    public T getElementAtBigNull(BigInteger index) throws IllegalArgumentException {
        return this.maybeGetElementAtBig(index).getValueNull();
    }
    public T getElementAtBig(BigInteger index) throws NoSuchElementException, IllegalArgumentException {
        return this.maybeGetElementAtBig(index).getValue();
    }
    public Maybe<T> maybeGetElementAt(long index) throws IllegalArgumentException {
        return this.maybeGetElementAtBig(BigInteger.valueOf(index));
    }
    public T getElementAtNull(long index) throws IllegalArgumentException {
        return this.maybeGetElementAt(index).getValueNull();
    }
    public T getElementAt(long index) throws NoSuchElementException, IllegalArgumentException {
        return this.maybeGetElementAt(index).getValue();
    }


    public Maybe<T> maybeGetElementAtBigRecursive(BigInteger index) throws IllegalArgumentException {
        if (index.compareTo(LList.zero) < 0) {
            throw new IllegalArgumentException();
        }
        if (!this.value.isPresent()) {
            return new Maybe<>();
        }
        if (index.equals(BigInteger.valueOf(0))) {
            return new Maybe<>(this.getHead());
        }
        return this.getTail().
            maybeGetElementAtBigRecursive(index.subtract(BigInteger.valueOf(1)));
    }
    public T getElementAtBigNullRecursive(BigInteger index) throws IllegalArgumentException {
        return this.maybeGetElementAtBigRecursive(index).getValueNull();
    }
    public T getElementAtBigRecursive(BigInteger index) throws NoSuchElementException, IllegalArgumentException {
        return this.maybeGetElementAtBigRecursive(index).getValue();
    }
    public Maybe<T> maybeGetElementAtRecursive(long index) throws IllegalArgumentException {
        return this.maybeGetElementAtBigRecursive(BigInteger.valueOf(index));
    }
    public T getElementAtNullRecursive(long index) throws IllegalArgumentException {
        return this.maybeGetElementAtRecursive(index).getValueNull();
    }
    public T getElementAtRecursive(long index) throws NoSuchElementException, IllegalArgumentException {
        return this.maybeGetElementAtRecursive(index).getValue();
    }

    // Get individual components as Maybe
    public Maybe<T> maybeGetHead() {
        if (this.value.isPresent()) {
            return new Maybe<>(this.value.getValue().getValue1());
        }
        return new Maybe<>();
    }
    public Maybe<LList<T>> maybeGetTail() {
        if (this.value.isPresent()) {
            return new Maybe<>(this.value.getValue().getValue2());
        }
        return new Maybe<>();
    }

    // Get individual components with null representing absence
    // This breaks any code that expects null values to be preserved
    public T getHeadNull() {
        if (this.value.isPresent()) {
            return this.value.getValue().getValue1();
        }
        return null;
    }
    public LList<T> getTailNull() {
        if (this.value.isPresent()) {
            return this.value.getValue().getValue2();
        }
        return null;
    }

    // Get the tail of the list with an empty list representing absence
    // This does not distinguish empty and singleton lists
    public LList<T> getTailList() {
        if (this.value.isPresent()) {
            return this.value.getValue().getValue2();
        }
        return new LList<>();
    }

    // Get a value that’s known to exist
    // Throws NoSuchElementException if it doesn’t
    public T getHead() throws NoSuchElementException {
        if (this.value.isPresent()) {
            return this.value.getValue().getValue1();
        }
        throw new NoSuchElementException();
    }
    public LList<T> getTail() throws NoSuchElementException {
        if (this.value.isPresent()) {
            return this.value.getValue().getValue2();
        }
        throw new NoSuchElementException();
    }

    // More direct getters for the value property
    public Maybe<Pair<T,LList<T>>> maybeGetValue() {
        return this.value;
    }
    public Pair<T,LList<T>> getValueNull() {
        if (this.value.isPresent()) {
            return this.value.getValue();
        }
        return null;
    }
    // #endregion

    // #region Utilities
    // Is the list empty?
    public boolean isEmpty() {
        return !this.value.isPresent();
    }

    // Does the list only have one element?
    public boolean isSingleton() {
        if (this.value.isPresent()) {
            return this.getTail().isEmpty();
        }
        return false;
    }

    // Does the list have less than two elements?
    public boolean isEmptyOrSingleton() {
        if (this.value.isPresent()) {
            return this.getTail().isEmpty();
        }
        return true;
    }

    // Gets the length of the list
    public long getLength() {
        var tail = this;
        long length = 0;
        while (!tail.isEmpty()) {
            length++;
            tail = tail.getTail();
        }
        return length;
    }
    public BigInteger getBigLength() {
        var tail = this;
        var length = LList.zero;
        while (!tail.isEmpty()) {
            length = length.add(LList.one);
            tail = tail.getTail();
        }
        return length;
    }
    public long getLengthRecursive() {
        if (this.isEmpty()) {
            return 0;
        }
        return this.getTail().getLengthRecursive() + 1;
    }
    public BigInteger getBigLengthRecursive() {
        if (this.isEmpty()) {
            return LList.zero;
        }
        return this.getTail().getBigLengthRecursive().add(LList.one);
    }
    // #endregion Utilities

    // This can’t be two separate values for coherence
    // Both need to be present or absent together
    private Maybe<Pair<T,LList<T>>> value;

    private static BigInteger zero = BigInteger.valueOf(0);
    private static BigInteger one = BigInteger.valueOf(1);
}
