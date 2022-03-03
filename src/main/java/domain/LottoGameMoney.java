package domain;

import constant.LottoConstant;

public class LottoGameMoney {

    private static final int MINIMUM_AMOUNT = 0;
    private static final int REMINDER_STANDARD = 0;

    private final int amount;

    public LottoGameMoney(int amount) {
        validateRange(amount);
        this.amount = amount;
    }

    private void validateRange(int amount) {
        if (amount <= MINIMUM_AMOUNT) {
            throw new IllegalArgumentException("금액은 " + MINIMUM_AMOUNT + "이하일 수 없습니다.");
        }
        if (amount % LottoConstant.LOTTO_PRICE != REMINDER_STANDARD) {
            throw new IllegalArgumentException("로또 구매 금액은 " + LottoConstant.LOTTO_PRICE + "원 단위여야 합니다.");
        }
    }

    public LottoCount getPurchaseLottoCount(int manualLottoCount) {
        checkPurchasableLottoCount(manualLottoCount);

        return new LottoCount(manualLottoCount, purchasableLottoCount() - manualLottoCount);
    }

    public void checkPurchasableLottoCount(int lottoCount) {
        if (lottoCount < 0 || lottoCount > purchasableLottoCount()) {
            throw new IllegalArgumentException("구매할 수 있는 로또 갯수는 0~" + purchasableLottoCount() + "개 입니다.");
        }
    }

    public int purchasableLottoCount() {
        return amount / LottoConstant.LOTTO_PRICE;
    }

    public int getAmount() {
        return amount;
    }
}
