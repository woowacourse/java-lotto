package lotto.domain;

import java.util.Objects;

public class PurchaseAmount {
    private static final int LOTTO_PRICE = 1000;

    private final int price;
    private final int manualCount;

    private PurchaseAmount(int price, int manualCount) {
        checkMinimumAmount(price);
        checkChange(price);
        checkManualPrice(price, manualCount);
        this.price = price;
        this.manualCount = manualCount;
    }

    private void checkManualPrice(int price, int manualCount) {
        if (price < manualCount * LOTTO_PRICE) {
            throw new IllegalArgumentException("금액 이상의 수량을 입력하였습니다.");
        }
        if (manualCount < 0) {
            throw new IllegalArgumentException("수량은 음수일 수 없습니다.");
        }
    }

    private void checkMinimumAmount(int price) {
        if (price < LOTTO_PRICE) {
            throw new IllegalArgumentException("최소 금액보다 적은 금액입니다.");
        }
    }

    private void checkChange(int price) {
        if (price % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("거스름돈이 존재합니다.");
        }
    }

    public static PurchaseAmount of(int price, int manualCount) {
        return new PurchaseAmount(price, manualCount);
    }

    public int purchaseAutoCount() {
        return (price - manualCount) / LOTTO_PRICE;
    }

    public int purchaseManualCount() {
        return manualCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseAmount purchaseAmount = (PurchaseAmount) o;
        return price == purchaseAmount.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(price);
    }
}
