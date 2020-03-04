package domain;

public class Money {
    private static final int LOTTO_PRICE = 1_000;
    private static final int NEGATIVE_CRITERIA_POINT = 0;
    private static final int PERCENTAGE = 100;

    private int purchaseAmount;

    public Money(final int purchaseAmount) {
        checkNegativeAmount(purchaseAmount);
        checkUnderLottoPrice(purchaseAmount);
        this.purchaseAmount = purchaseAmount;
    }

    public int calculateProfitRatio(final LottoResult lottoResult) {
        return lottoResult.calculateTotalProfit() / (getLottoCount() * LOTTO_PRICE) * PERCENTAGE;
    }

    private void checkUnderLottoPrice(final int purchaseAmount) {
        if (purchaseAmount < LOTTO_PRICE) {
            throw new IllegalArgumentException("로또 한장 가격보다 낮은 금액을 입력하셨습니다.");
        }
    }

    private void checkNegativeAmount(final int purchaseAmount) {
        if (purchaseAmount < NEGATIVE_CRITERIA_POINT) {
            throw new IllegalArgumentException("구매 금액은 음수일 수 없습니다.");
        }
    }

    public int getLottoCount() {
        return purchaseAmount / LOTTO_PRICE;
    }
}
