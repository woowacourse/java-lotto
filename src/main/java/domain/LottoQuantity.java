package domain;

public class LottoQuantity {

    private static final int MINIMUM_QUANTITY = 0;

    private final int manualLotto;
    private final int autoLotto;

    private LottoQuantity(final int manualLotto, final int autoLotto) {
        this.manualLotto = manualLotto;
        this.autoLotto = autoLotto;
    }

    public static LottoQuantity of(int manualLottoQuantity, Money money) {
        checkValidManualLottoQuantity(manualLottoQuantity);
        checkEnoughMoney(manualLottoQuantity, money);

        int autoLottoQuantity = money.getTotalQuantityForPurchase() - manualLottoQuantity;
        return new LottoQuantity(manualLottoQuantity, autoLottoQuantity);
    }

    private static void checkValidManualLottoQuantity(int manualLottoQuantity) {
        if (manualLottoQuantity < MINIMUM_QUANTITY) {
            throw new IllegalArgumentException("구매 수량은 반드시 양수여야 합니다.");
        }
    }

    private static void checkEnoughMoney(int manualLottoQuantity, Money money) {
        if (manualLottoQuantity > money.getTotalQuantityForPurchase()) {
            throw new IllegalArgumentException("입력한 수량만큼 구매할 수 없습니다.");
        }
    }

    public int getManualLotto() {
        return manualLotto;
    }

    public int getAutoLotto() {
        return autoLotto;
    }
}
