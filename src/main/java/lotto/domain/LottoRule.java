package lotto.domain;

public enum LottoRule {
    MIN_LOTTO_NUMBER(1),
    MAX_LOTTO_NUMBER(45),
    LOTTO_SIZE(6),
    MONEY_PER_LOTTO(1000);

    private final int number;

    LottoRule(int number) {
        this.number = number;
    }

    public int get() {
        return this.number;
    }
}
