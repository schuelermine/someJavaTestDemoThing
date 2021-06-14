import java.util.NoSuchElementException;

// Utility class for optional values
// Differs from Optional in that it can hold null
public class Maybe<T> {
    // #region Constructors
    public Maybe() {
        this.value = null;
        this.isPresent = false;
    }
    public Maybe(T x) {
        this.value = x;
        this.isPresent = true;
    }
    public static <U> Maybe<U> fromNull(U x) {
        if (x == null) {
            return new Maybe<>();
        }
        return new Maybe<>(x);
    }
    // #endregion

    // #region Accessors
    public T getValue() throws NoSuchElementException {
        if (this.isPresent) {
            return this.value;
        } else {
            throw new NoSuchElementException();
        }
    }
    public T getValueNull() {
        if (this.isPresent) {
            return this.value;
        } else {
            return null;
        }
    }
    // #endregion

    public boolean isPresent() {
        return this.isPresent;
    }
    
    private T value;
    private boolean isPresent;
}
