package lotto.domain;

public class LottoNumber {

    private static final String ERROR_NUMBER_RANGE = "로또 번호가 범위를 벗어났습니다.";
    private static final int MINIMUM_RANGE = 1;
    private static final int MAXIMUM_RANGE = 45;

    private final int number;

    public LottoNumber(int number) {
        this.number = number;
        validateRange();
    }

    private void validateRange() {
        if (number < MINIMUM_RANGE || number > MAXIMUM_RANGE) {
            throw new RuntimeException(ERROR_NUMBER_RANGE);
        }
    }
}
