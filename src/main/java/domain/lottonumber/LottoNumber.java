package domain.lottonumber;

public class LottoNumber implements Comparable<LottoNumber> {
    private static final String ERROR_BOUND_MESSAGE = "1부터 45 사이의 숫자만 입력 가능합니다.";
    private static final int LOTTO_UNDER_BOUND = 1;
    private static final int LOTTO_UPPER_BOUND = 45;

    private int number;

    public LottoNumber(int number) {
        validateBound(number);
        this.number = number;
    }

    private static void validateBound(int number) {
        if (number < LOTTO_UNDER_BOUND || number > LOTTO_UPPER_BOUND) {
            throw new IllegalArgumentException(ERROR_BOUND_MESSAGE);
        }
    }

    @Override
    public int compareTo(LottoNumber o) {
        return Integer.compare(number, o.number);
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
