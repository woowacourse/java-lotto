package domain;

import java.util.Objects;

public class LottoQuantity {
    private static final String ERROR_MESSAGE_FOR_INVALID_TRAIL_NUMBER = "개수는 1 보다 작을 수 없습니다.";
    private static final int QUANTITY_CRITERIA = 0;
    private final int lottoQuantity;

    public LottoQuantity(int lottoQuantity) {
        validatePositive(lottoQuantity);

        this.lottoQuantity = lottoQuantity;
    }

    private void validatePositive(int lottoQuantity) {
        if (lottoQuantity <= QUANTITY_CRITERIA) {
            throw new IllegalArgumentException(ERROR_MESSAGE_FOR_INVALID_TRAIL_NUMBER);
        }
    }

    public int getLottoQuantity() {
        return lottoQuantity;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        LottoQuantity that = (LottoQuantity) object;
        return lottoQuantity == that.lottoQuantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoQuantity);
    }

    @Override
    public String toString() {
        return "LottoQuantity{" + "lottoQuantity=" + lottoQuantity + '}';
    }
}
