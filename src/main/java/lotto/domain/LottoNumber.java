package lotto.domain;

public class LottoNumber {
    private static final int MINIMUM = 1;
    private static final int MAXIMUM = 45;
    private final int number;
    private final boolean isBonusNumber;

    public LottoNumber(int value) {
        this(value, false);
    }

    public LottoNumber(int value, boolean isBonusNumber) {
        validateRange(value);
        this.number = value;
        this.isBonusNumber = isBonusNumber;
    }

    private void validateRange(int value) {
        if (value < MINIMUM || MAXIMUM < value) {
            throw new IllegalArgumentException("로또 번호는 1이상, 45이하여야 합니다.");
        }
    }

    public boolean isBonusNumber() {
        return isBonusNumber;
    }
}
