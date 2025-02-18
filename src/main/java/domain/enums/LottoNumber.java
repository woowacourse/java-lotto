package domain.enums;

public enum LottoNumber {
    MIN_RANGE(1),
    MAX_RANGE(45),
    QUANTITY(6),
    ;

    private final int number;

    LottoNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
