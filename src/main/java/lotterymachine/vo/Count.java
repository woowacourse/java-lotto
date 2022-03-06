package lotterymachine.vo;

import java.util.Objects;

public class Count {
    private static final String IS_NOT_INTEGER_EXCEPTION = "음수가 아닌 정수를 입력해야합니다.";
    private static final String NOT_PURCHASABLE_COUNT_EXCEPTION = "구매가능한 로또 개수를 초과하였습니다.";

    private final int number;

    private Count(int number) {
        validateInteger(number);
        this.number = number;
    }

    public static Count from(int number) {
        return new Count(number);
    }

    public static Count of(Count total, int number) {
        validateRange(total.getNumber(), number);
        return new Count(number);
    }

    public Count increase() {
        return new Count(this.number + 1);
    }

    public Count subtract(Count manualTickets) {
        return new Count(this.number - manualTickets.getNumber());
    }

    public boolean isInteger() {
        return this.number > 0;
    }

    public int getNumber() {
        return number;
    }

    private void validateInteger(int number) {
        if (number < 0) {
            throw new IllegalArgumentException(IS_NOT_INTEGER_EXCEPTION);
        }
    }

    private static void validateRange(int total, int number) {
        if (total < number) {
            throw new IllegalArgumentException(NOT_PURCHASABLE_COUNT_EXCEPTION);
        }
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
