package domain;

import domain.enums.LottoMoney;

public class Money {
    private final int originMoney;

    public Money(int originMoney) {
        validate(originMoney);
        this.originMoney = originMoney;
    }

    private void validate(int originMoney) {
        if (originMoney < 0) {
            throw new IllegalArgumentException("돈은 양의 정수여야 합니다.");
        }
        if (originMoney < LottoMoney.PRICE.getValue()) {
            throw new IllegalArgumentException("돈은 1,000원 이상이어야 합니다.");
        }
    }

    public int getBuyableLottoCount() {
        return originMoney / LottoMoney.PRICE.getValue();
    }

    public int getChange() {
        return originMoney % LottoMoney.PRICE.getValue();
    }
}
