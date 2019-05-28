package com.woowacourse.lotto.domain;

import java.util.Objects;

public class BuyingMoney {

    private final int buyingMoney;

    public BuyingMoney(int i) {
        this.buyingMoney = i;
        checkRange();
    }

    private void checkRange() {
        if (buyingMoney < Lotto.UNIT_PRICE) {
            throw new IllegalArgumentException("구입 금액은 최소 한 장을 살 수 있어야 합니다.");
        }
    }

    public int getQuantity() {
        return buyingMoney / Lotto.UNIT_PRICE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuyingMoney that = (BuyingMoney) o;
        return buyingMoney == that.buyingMoney;
    }

    @Override
    public int hashCode() {
        return Objects.hash(buyingMoney);
    }
}
