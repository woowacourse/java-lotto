package lotto.domain;

public class PurchaseInformation {
    private static final int MIN_PURCHASE_AMOUNT = 0;

    private final int numberOfManualLottos;
    private final int numberOfAutoLottos;

    public PurchaseInformation(final Money money, final int numberOfManualLottos) {
        int maxNumberOfPurchase = money.calculateMaxNumberOfPurchase();
        checkInvalidNumberOfManualLottos(maxNumberOfPurchase, numberOfManualLottos);

        this.numberOfManualLottos = numberOfManualLottos;
        this.numberOfAutoLottos = maxNumberOfPurchase - numberOfManualLottos;
    }

    private void checkInvalidNumberOfManualLottos(final int maxNumberOfPurchase, final int numberOfManualLottos) {
        if (numberOfManualLottos < MIN_PURCHASE_AMOUNT) {
            throw new IllegalArgumentException(MIN_PURCHASE_AMOUNT + "이상의 개수를 입력해주세요.");
        }
        if (maxNumberOfPurchase < numberOfManualLottos) {
            throw new IllegalArgumentException("구입금액이 부족합니다.");
        }
    }

    public int getNumberOfManualLottos() {
        return numberOfManualLottos;
    }

    public int getNumberOfAutoLottos() {
        return numberOfAutoLottos;
    }
}
