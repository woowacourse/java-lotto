package lotto.domain;

public class PurchaseInformation {
    private static final int LOTTO_PRICE = 1000;
    private static final int MAX_LOTTO_PRICE = 1_000_000_000;
    private static final int MIN_PURCHASE_AMOUNT = 0;

    private final int numberOfManualLottos;
    private final int numberOfAutoLottos;

    public PurchaseInformation(final int money, final int numberOfManualLottos) {
        checkInvalidMoney(money);

        int maxNumberOfPurchase = money / LOTTO_PRICE;
        checkInvalidNumberOfManualLottos(maxNumberOfPurchase, numberOfManualLottos);

        this.numberOfManualLottos = numberOfManualLottos;
        this.numberOfAutoLottos = maxNumberOfPurchase - numberOfManualLottos;
    }

    private void checkInvalidMoney(final int money) {
        if (money < LOTTO_PRICE || money > MAX_LOTTO_PRICE) {
            throw new IllegalArgumentException("로또 구입 가능 범위를 벗어났습니다.");
        }
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
