package lotterymachine.vo;

import java.util.Objects;

public class Count {
    private int number;

    private Count(int number) {
        this.number = number;
    }

    public static Count from(int number) {
        return new Count(number);
    }

    public Count increase() {
        this.number++;
        return this;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Count count = (Count) o;
        return number == count.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
