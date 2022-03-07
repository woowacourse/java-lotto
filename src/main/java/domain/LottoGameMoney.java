package domain;

public class LottoGameMoney {

    private static final int MINIMUM_AMOUNT = 0;
    private static final int LOTTO_PRICE = 1000;

    private final int amount;

    public LottoGameMoney(int amount) {
        validateAmount(amount);
        this.amount = amount;
    }

    private void validateAmount(int amount) {
        if (amount <= MINIMUM_AMOUNT) {
            throw new IllegalArgumentException("금액은 " + MINIMUM_AMOUNT + "이하일 수 없습니다.");
        }
        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("로또 구매 금액은 " + LOTTO_PRICE + "원 단위여야 합니다.");
        }
    }

    public LottoCount createLottoCount(int manualLottoCount) {
        validateLottoCount(manualLottoCount);
        return new LottoCount(manualLottoCount, purchasableLottoCount() - manualLottoCount);
    }

    private void validateLottoCount(int lottoCount) {
        if (lottoCount < 0 || lottoCount > purchasableLottoCount()) {
            throw new IllegalArgumentException("구매할 수 있는 로또 갯수는 0~" + purchasableLottoCount() + "개 입니다.");
        }
    }

    private int purchasableLottoCount() {
        return amount / LOTTO_PRICE;
    }

    public int amount() {
        return amount;
    }
}
