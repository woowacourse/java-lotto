package domain.player;

import utils.ExceptionMessage;

public class Money {

    static final int MINIMUM_PURCHASE_AMOUNT = 1000;

<<<<<<< HEAD
<<<<<<< HEAD
    private int amount;
=======
    private final int amount;
>>>>>>> d5f0ef8 (refactor: 패키지 분리)
=======
    private int amount;
>>>>>>> 440c90c (refactor : Player 로또 구매 역할 분리)

    public Money(int amount) {
        validateAmount(amount);
        validateUnit(amount);
        this.amount = amount;
    }

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 440c90c (refactor : Player 로또 구매 역할 분리)
    public void deductMoney() {
        amount -= MINIMUM_PURCHASE_AMOUNT;
    }

    public boolean isBiggerThanLottoPrice() {
        return amount >= MINIMUM_PURCHASE_AMOUNT;
<<<<<<< HEAD
=======
    public int determineQuantity() {
        return amount / MINIMUM_PURCHASE_AMOUNT;
>>>>>>> d5f0ef8 (refactor: 패키지 분리)
=======
>>>>>>> 440c90c (refactor : Player 로또 구매 역할 분리)
    }

=======
>>>>>>> 4022ea6 (refactor: 메서드 위치 변경)
    private void validateAmount(int amount) {
        if (amount < MINIMUM_PURCHASE_AMOUNT) {
            throw new IllegalArgumentException(ExceptionMessage.NOT_ENOUGH_MONEY);
        }
    }

    private void validateUnit(int amount) {
        if (amount % MINIMUM_PURCHASE_AMOUNT != 0) {
            throw new IllegalArgumentException(ExceptionMessage.NOT_1000_UNIT);
        }
    }

    public void deductMoney() {
        amount -= MINIMUM_PURCHASE_AMOUNT;
    }

    public boolean isBiggerThanLottoPrice() {
        return amount >= MINIMUM_PURCHASE_AMOUNT;
    }
}
