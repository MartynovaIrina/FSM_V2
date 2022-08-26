import java.util.Objects;

public class Pair<T, E> {
    private T first;
    private E second;

    public Pair(T first, E second) {
        setFirst(first);
        setSecond(second);
    }

    public T getFirst() {
        return this.first;
    }

    public E getSecond() {
        return this.second;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            Pair<?, ?> pair = (Pair)o;
            return Objects.equals(this.first, pair.first) && Objects.equals(this.second, pair.second);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.first, this.second});
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public void setSecond(E second) {
        this.second = second;
    }
}
