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
    public Maybe<T> maybeGetElementAt(BigInteger index) throws IllegalArgumentException {
        if (index.compareTo(BigInteger.valueOf(0)) < 0) {
            throw new IllegalArgumentException();
        }
        if (!this.value.isPresent()) {
            return new Maybe<>();
        }
        if (index.equals(BigInteger.valueOf(0))) {
            return new Maybe<>(this.getHead());
        }
        return
            this.getTail().
            maybeGetElementAt(index.subtract(BigInteger.valueOf(1)));
    }
    public T getElementAtNull(BigInteger index) throws IllegalArgumentException {
        return this.maybeGetElementAt(index).getValueNull();
    }
    public T getElementAt(BigInteger index) throws NoSuchElementException, IllegalArgumentException {
        return this.maybeGetElementAt(index).getValue();
    }
    public Maybe<T> maybeGetElementAtLong(long index) throws IllegalArgumentException {
        return this.maybeGetElementAt(BigInteger.valueOf(index));
    }
    public T getElementAtLongNull(long index) throws IllegalArgumentException {
        return this.maybeGetElementAtLong(index).getValueNull();
    }
    public T getElementAtLong(long index) throws NoSuchElementException, IllegalArgumentException {
        return this.maybeGetElementAtLong(index).getValue();
    }

    // Get individual components as Maybe
    public Maybe<T> maybeGetHead() {
        if (this.value.isPresent()) {
            return new Maybe<>(this.value.getValue().getValue1());
        } else {
            return new Maybe<>();
        }
    }
    public Maybe<LList<T>> maybeGetTail() {
        if (this.value.isPresent()) {
            return new Maybe<>(this.value.getValue().getValue2());
        } else {
            return new Maybe<>();
        }
    }

    // Get individual components with null representing absence
    // This breaks any code that expects null values to be preserved
    public T getHeadNull() {
        if (this.value.isPresent()) {
            return this.value.getValue().getValue1();
        } else {
            return null;
        }
    }
    public LList<T> getTailNull() {
        if (this.value.isPresent()) {
            return this.value.getValue().getValue2();
        } else {
            return null;
        }
    }

    // Get the tail of the list with an empty list representing absence
    // This does not distinguish empty and singleton lists
    public LList<T> getTailList() {
        if (this.value.isPresent()) {
            return this.value.getValue().getValue2();
        } else {
            return new LList<>();
        }
    }

    // Get a value that’s known to exist
    // Throws NoSuchElementException if it doesn’t
    public T getHead() throws NoSuchElementException {
        if (this.value.isPresent()) {
            return this.value.getValue().getValue1();
        } else {
            throw new NoSuchElementException();
        }
    }
    public LList<T> getTail() throws NoSuchElementException {
        if (this.value.isPresent()) {
            return this.value.getValue().getValue2();
        } else {
            throw new NoSuchElementException();
        }
    }

    // More direct getters for the value property
    public Maybe<Pair<T,LList<T>>> maybeGetValue() {
        return this.value;
    }
    public Pair<T,LList<T>> getValueNull() {
        if (this.value.isPresent()) {
            return this.value.getValue();
        } else {
            return null;
        }
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
        } else {
            return false;
        }
    }

    // Does the list have less than two elements?
    public boolean isEmptyOrSingleton() {
        if (this.value.isPresent()) {
            return this.getTail().isEmpty();
        } else {
            return true;
        }
    }

    // Gets the length of the list
    public BigInteger getLength() {
        if (this.isEmpty()) {
            return LList.zero;
        }
        return this.getTail().getLength().add(LList.one);
    }
    // #endregion Utilities

    // This can’t be two separate values for coherence
    // Both need to be present or absent together
    private Maybe<Pair<T,LList<T>>> value;

    private static BigInteger zero = BigInteger.valueOf(0);
    private static BigInteger one = BigInteger.valueOf(1);
}
