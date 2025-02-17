package model;

public class Wallet {
    private static final int LOTTO_PRICE_PER_ONE = 1000;
    private final int money;

    public Wallet(final int money) {
        validateDivideByThousand(money);
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    private void validateDivideByThousand(int inputMoney) {
        if (inputMoney % LOTTO_PRICE_PER_ONE != 0) {
            throw new IllegalArgumentException("천원단위로 입력해주세요.");
        }
    }

    public int getPurchasableQuantity() {
        return money / LOTTO_PRICE_PER_ONE;
    }
}
