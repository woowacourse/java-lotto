package lotto.model.number;

public class LottoNumber {

    private static final String NUMBER_RANGE_ERROR_MESSAGE = "[ERROR] 유효한 숫자가 아닙니다.";
    private static final int LOTTO_NUMBER_MINIMUM_RANGE = 1;
    private static final int LOTTO_NUMBER_MAXIMUM_RANGE = 45;

    protected int number;

    public LottoNumber(int number) {
        validateRange(number);
        this.number = number;
    }

    protected void validateRange(int number) {
        if (number < LOTTO_NUMBER_MINIMUM_RANGE || number > LOTTO_NUMBER_MAXIMUM_RANGE) {
            throw new RuntimeException(NUMBER_RANGE_ERROR_MESSAGE);
        }
    }

    public int getLottoNumber() {
        return number;
    }
}
