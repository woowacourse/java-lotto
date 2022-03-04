package lotto.domain.vo;

import java.util.Objects;

public class LottoPurchaseMoney {

    private static final int PRICE_CRITERION = 1000;

    private final int price;

    private LottoPurchaseMoney(int price) {
        validateCriterion(price);
        this.price = price;
    }

    public static LottoPurchaseMoney create(int money) {
        return new LottoPurchaseMoney(money);
    }

    public int calculate(int manualCount) {
        int totalCount = this.price / PRICE_CRITERION;

        validateManualCount(manualCount, totalCount);

        return totalCount - manualCount;
    }

    public int getPrice() {
        return this.price;
    }

    private void validateCriterion(int price) {
        if (price < PRICE_CRITERION) {
            throw new IllegalArgumentException("티켓은 1000원 입니다. 1000원 이상 입력해주세요.");
        }
    }

    private void validateManualCount(int manualCount, int totalCount) {
        if (manualCount > totalCount) {
            throw new IllegalArgumentException("전체 금액보다 큰 수동 티켓을 구매할 수 없습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoPurchaseMoney that = (LottoPurchaseMoney) o;
        return price == that.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(price);
    }
}
