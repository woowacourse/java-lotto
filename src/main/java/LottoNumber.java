import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {

    private static final String INVALID_RANGE_MESSAGE = "[ERROR] 번호는 1부터 45 이내의 숫자여야 합니다.";
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;

    private final int number;

    private LottoNumber(int number) {
        validateRange(number);
        this.number = number;
    }

    public static LottoNumber from(int number) {
        return new LottoNumber(number);
    }

    private void validateRange(int number) {
        if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException(INVALID_RANGE_MESSAGE);
        }
    }

    public static int getMaxLottoNumber() {
        return MAX_LOTTO_NUMBER;
    }

    public static int getMinLottoNumber() {
        return MIN_LOTTO_NUMBER;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }

    @Override
    public int compareTo(LottoNumber o) {
        return number - o.number;
    }
}
