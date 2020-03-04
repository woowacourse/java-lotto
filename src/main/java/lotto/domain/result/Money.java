package lotto.domain.result;

import lotto.domain.exception.PurchaseMoneyLackException;

import java.util.Objects;

public class Money {
    static final int TICKET_PRICE = 1_000;
    private static final String INVALID_PURCHASE_NUMBER_EXCEPTION_MESSAGE = "최소 구매 금액 이하의 입력이 들어왔습니다.";

    private double money;

    public Money(double money, int lowerBound) {
        validateMinimumMoney(money, lowerBound);
        this.money = money;
    }

    private void validateMinimumMoney(double money, int lowerBound) {
        if (money < lowerBound) {
            throw new PurchaseMoneyLackException(INVALID_PURCHASE_NUMBER_EXCEPTION_MESSAGE);
        }
    }

    public void validateManualLottoMoney(int manualLottoSize) {
        if (calculateRound() < manualLottoSize) {
            throw new PurchaseMoneyLackException(INVALID_PURCHASE_NUMBER_EXCEPTION_MESSAGE);
        }
    }

    public int calculateRound() {
        return (int) money / TICKET_PRICE;
    }

    public int devide(int value) {
        return (int) money / value;
    }

    public void subtract(double ticketPrice) {
        this.money -= ticketPrice;
    }

    public double getMoney() {
        return money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money1 = (Money) o;
        return Double.compare(money1.money, money) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }
}

