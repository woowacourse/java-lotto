package domain;

import message.ErrorMessage;

public class Money {
    private final int originMoney;

    public Money(int originMoney) {
        validate(originMoney);
        this.originMoney = originMoney;
    }

    private void validate(int originMoney) {
        if (originMoney < 0) {
            throw new IllegalArgumentException(ErrorMessage.NUMBER_FORMAT.getMessage());
        }
        if (originMoney < 1000) {
            throw new IllegalArgumentException(ErrorMessage.MONEY_MINIMUM.getMessage());
        }
    }

    public int getBuyableLottoCount() {
        return originMoney / 1000;
    }

    public int getChange() {
        return originMoney % 1000;
    }
}
