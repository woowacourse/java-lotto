package domain;

public class LottoNumber {
    protected static final int NUMBER_MIN = 1;
    protected static final int NUMBER_MAX = 45;

    private final int value;

    public LottoNumber(int value) {
        validateLottoNumber(value);
        this.value = value;
    }

    private void validateLottoNumber(int value) {
        if (value < NUMBER_MIN || value > NUMBER_MAX) {
            throw new IllegalArgumentException("로또 숫자 범위 외 숫자입니다.");
        }
    }
}
