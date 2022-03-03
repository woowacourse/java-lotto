package domain;

import static domain.Lotto.LOTTO_PRICE;

public class Wallet {
    private static final int EXPECTED_REMAIN = 0;
    private static final int ZERO_FOR_EMPTY_WALLET = 0;
    private static final int MINIMUM_MANUAL_LOTTO_QUANTITY_TO_BUY = 1;
    private static final String ERROR_MESSAGE_FOR_INVALID_INPUT_MONEY = "금액은 1000원 이상, 1000원 단위로 입력해주세요";
    private static final String ERROR_MESSAGE_FOR_BUYING_LESS_THEN_ONE = "구매 수량은 1장 이상이어야 합니다";
    private static final String ERROR_MESSAGE_FOR_NOT_ENOUGH_MONEY = " 이상 구매할 수 없습니다 : ";
    private static final int EMPTY_AUTO_QUANTITY = 0;

    private int currentBalance;
    private int manualQuantity;
    private int autoQuantity;

    public Wallet(int inputMoney) {
        validate(inputMoney);
        this.currentBalance = inputMoney;
    }

    private void validate(int money) {
        if (isInvalidInputMoney(money)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_FOR_INVALID_INPUT_MONEY);
        }
    }

    private boolean isInvalidInputMoney(int money) {
        return money < LOTTO_PRICE || money % LOTTO_PRICE != EXPECTED_REMAIN;
    }

    public void buyManualLottoByQuantity(int quantity) {
        validateQuantity(quantity);

        this.currentBalance -= (LOTTO_PRICE * quantity);
        this.manualQuantity = quantity;
    }

    private void validateQuantity(int quantity) {
        if (quantity < MINIMUM_MANUAL_LOTTO_QUANTITY_TO_BUY) {
            throw new IllegalArgumentException(ERROR_MESSAGE_FOR_BUYING_LESS_THEN_ONE);
        }

        if (this.currentBalance < LOTTO_PRICE * quantity) {
            throw new IllegalArgumentException(
                    this.currentBalance / LOTTO_PRICE + ERROR_MESSAGE_FOR_NOT_ENOUGH_MONEY + quantity);
        }
    }

    public void buyAutoLottoWithCurrentBalance() {
        this.autoQuantity = this.currentBalance / LOTTO_PRICE;
        this.currentBalance -= this.autoQuantity * LOTTO_PRICE;
    }

    public boolean isEmpty() {
        return this.currentBalance == ZERO_FOR_EMPTY_WALLET;
    }

    public boolean hasNoAutoQuantity() {
        return currentBalance == ZERO_FOR_EMPTY_WALLET && autoQuantity == EMPTY_AUTO_QUANTITY;
    }

    public int getCurrentBalance() {
        return currentBalance;
    }

    public int getManualQuantity() {
        return manualQuantity;
    }

    public int getAutoQuantity() {
        return autoQuantity;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "currentBalance=" + currentBalance +
                ", manualQuantity=" + manualQuantity +
                ", autoQuantity=" + autoQuantity +
                '}';
    }
}
