package lotto.domain.machine;

import lotto.domain.machine.exeption.InvalidMinimumMoneyException;

public class MoneyProcessor {
    private static final int LOTTO_PRICE = 1000;
    private final int insertedMoney;

    private MoneyProcessor(int insertedMoney) {
        this.insertedMoney = insertedMoney;
    }

    public static MoneyProcessor of(int insertedMoney) {
        validateMinumumMoney(insertedMoney);
        return new MoneyProcessor(insertedMoney);
    }

    private static void validateMinumumMoney(int money) {
        if (money < LOTTO_PRICE) {
            throw new InvalidMinimumMoneyException("1장 이상 구입 가능한 금액을 넣어주세요.");
        }
    }

    public int getWholeTicketQuantity() {
        return this.insertedMoney / LOTTO_PRICE;
    }

    public int getRest() {
        return this.insertedMoney % LOTTO_PRICE;
    }
}
