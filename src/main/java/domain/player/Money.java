package domain.player;

import utils.ExceptionMessage;

public class Money {

    private static final int MINIMUM_PURCHASE_AMOUNT = 1000;

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
        this.amount = amount;
    }

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

    private void validateAmount(int amount) {
        if (amount < MINIMUM_PURCHASE_AMOUNT) {
            throw new IllegalArgumentException(ExceptionMessage.NOT_ENOUGH_MONEY);
        }
    }

    public int getAmount() {
        return amount;
    }
}
