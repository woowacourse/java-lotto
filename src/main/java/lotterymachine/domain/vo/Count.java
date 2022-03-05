package lotterymachine.domain.vo;

import java.util.Objects;

public class Count {
    private static final String INVALID_PASSIVITY_VALUE = "수동 로또 개수가 총 구매 개수 보다 높습니다.";

    private final int autoValue;
    private final int passivityValue;

    public Count(int passivityValue, int totalValue) {
        validatePassivityValue(passivityValue, totalValue);
        this.passivityValue = passivityValue;
        this.autoValue = totalValue - passivityValue;
    }

    public void validatePassivityValue(int passivityValue, int totalValue) {
        if (totalValue < passivityValue) {
            throw new IllegalArgumentException(INVALID_PASSIVITY_VALUE);
        }
    }

    public int getAutoValue() {
        return this.autoValue;
    }

    public int getPassivityValue() {
        return this.passivityValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Count count = (Count) o;
        return autoValue == count.autoValue && passivityValue == count.passivityValue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(autoValue, passivityValue);
    }
}
