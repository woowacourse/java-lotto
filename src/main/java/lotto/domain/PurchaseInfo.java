package lotto.domain;

public class PurchaseInfo {
    private static final String LOTTO_SIZE_MUST_BE_POSITIVE_ERROR_MSG = "로또 개수는 음수일 수 없습니다.";

    private final Money purchaseMoney;
    private final Money lottoPrice;
    private final int numberOfManualLotto;

    public PurchaseInfo(Money purchaseMoney, Money lottoPrice, int numberOfManualLotto) {
        validateNegative(numberOfManualLotto);
        this.purchaseMoney = purchaseMoney;
        this.lottoPrice = lottoPrice;
        this.numberOfManualLotto = numberOfManualLotto;
    }

    private void validateNegative(int numberOfManualLotto) {
        if (numberOfManualLotto < 0) {
            throw new IllegalArgumentException(LOTTO_SIZE_MUST_BE_POSITIVE_ERROR_MSG);
        }
    }

    public int numberOfManualLotto() {
        return numberOfManualLotto;
    }

    public int numberOfAutoLotto() {
        return calculateLeftMoney().divide(lottoPrice);
    }

    public Money calculateLeftMoney() {
        return purchaseMoney.minus(lottoPrice.multiply(numberOfManualLotto));
    }
}
