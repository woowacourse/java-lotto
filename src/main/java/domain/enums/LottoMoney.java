package domain.enums;

public enum LottoMoney {
    PRICE(1000),
    ;

    private final int value;

    LottoMoney(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
