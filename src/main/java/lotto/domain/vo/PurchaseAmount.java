package lotto.domain.vo;

import java.util.Objects;

public class PurchaseAmount {

    static final String NOT_NUMBER_MESSAGE = "[ERROR] 구입금액은 숫자여야 합니다.";
    static final String NOT_ENOUGH_MONEY = "[ERROR] 로또를 구매하려면 최소 천원 이상 투입해야 합니다.";

    static final int LOTTO_TICKET_PRICE = 1_000;

    private final int amount;

    public PurchaseAmount(String moneyString) {
        isNumber(moneyString);
        amount = Integer.parseInt(moneyString);
        isEnoughAmount();
    }

    public int amount() {
        return amount;
    }

    private void isNumber(String moneyString) {
        try {
            Integer.parseInt(moneyString);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_NUMBER_MESSAGE);
        }
    }

    private void isEnoughAmount() {
        if (amount < LOTTO_TICKET_PRICE) {
            throw new IllegalArgumentException(NOT_ENOUGH_MONEY);
        }
    }

    public int getAvailableTicketNumber() {
        return amount / LOTTO_TICKET_PRICE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PurchaseAmount purchaseAmount = (PurchaseAmount) o;
        return amount == purchaseAmount.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public String toString() {
        return "PurchaseAmount{" +
                "amount=" + amount +
                '}';
    }
}
