package domain;

public class BonusNumber {

    private final int value;

    public BonusNumber(final int value) {
        validateRange(value);
        this.value = value;
    }

    private void validateRange(final int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException("보너스 번호는 1부터 45 사이 숫자여야 합니다.");
        }
    }

    public int getValue() {
        return value;
    }
}
