package com.woowacourse.lotto.domain;

import java.util.Objects;

public class LottoQuantity {
    private final int quantity;

    private LottoQuantity(int quantity) {
        this.quantity = quantity;

        if (quantity < 0) {
            throw new IllegalArgumentException("로또 개수는 0 이상이어야 합니다.");
        }
    }

    public static LottoQuantity of(int quantity) {
        return new LottoQuantity(quantity);
    }

    public static LottoQuantity of(String quantityStr) {
        try {
            return new LottoQuantity(Integer.valueOf(quantityStr));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("올바르지 않은 개수 입력입니다: " + quantityStr);
        }
    }

    public int compareTo(int n) {
        return Integer.compare(quantity, n);
    }

    public int compareTo(LottoQuantity q) {
        return Integer.compare(this.quantity, q.quantity);
    }

    public int toInt() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoQuantity that = (LottoQuantity) o;
        return quantity == that.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity);
    }
}
