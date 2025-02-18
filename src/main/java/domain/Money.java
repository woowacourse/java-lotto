package domain;

import exception.IllegalPositiveIntegerException;

public class Money {
    public static final int LOTTO_PRICE = 1000;
    private final int originMoney;

    public Money(int originMoney) {
        validate(originMoney);
        this.originMoney = originMoney;
    }

    private void validate(int originMoney) {
        if (originMoney < 0) {
            throw new IllegalPositiveIntegerException();
        }
        if (originMoney < LOTTO_PRICE) {
            throw new IllegalArgumentException("돈은 1,000원 이상이어야 합니다.");
        }
    }

    public int getBuyableLottoCount() {
        return originMoney / LOTTO_PRICE;
    }

    public int getChange() {
        return originMoney % LOTTO_PRICE;
    }
}
