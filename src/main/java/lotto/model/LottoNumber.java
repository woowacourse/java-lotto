package lotto.model;

public class LottoNumber {

    private static final String NUMBER_RANGE_ERROR_MESSAGE = "[ERROR] 유효한 숫자가 아닙니다.";

    protected int number;

    public LottoNumber(int number) {
        validateRange(number);
        this.number = number;
    }

    protected void validateRange(int number) {
        if (number < 1 || number > 45) {
            throw new RuntimeException(NUMBER_RANGE_ERROR_MESSAGE);
        }
    }

    public int getLottoNumber() {
        return number;
    }
}
