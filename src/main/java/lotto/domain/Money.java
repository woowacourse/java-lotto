package lotto.domain;

import java.util.Objects;

import static lotto.domain.ticket.LottoTicket.LOTTO_PRICE;

public class Money {
    private static final String MESSAGE_FOR_NOT_ENOUGH_MONEY = "%d는 최소 구매 금액보다 작습니다.";
    private static final String MESSAGE_FOR_CANNOT_AFFORD = "투입 금액: %d, 구매 로또 수: %d - 잔액이 부족합니다.";

    private int money;

    public Money(int bettingMoney) {
        validateMoney(bettingMoney);
        this.money = bettingMoney;
    }

    private void validateMoney(int bettingMoney) {
        if (bettingMoney < LOTTO_PRICE) {
            throw new IllegalArgumentException(String.format(MESSAGE_FOR_NOT_ENOUGH_MONEY, bettingMoney));
        }
    }

    public void spendOnManualLotto(int numberOfTickets) {
        int totalPrice = numberOfTickets * LOTTO_PRICE;

        if (!canAfford(totalPrice)) {
            throw new IllegalArgumentException(String.format(MESSAGE_FOR_CANNOT_AFFORD, money, numberOfTickets));
        }
        money -= totalPrice;
    }

    private boolean canAfford(int totalPrice) {
        return !(money < totalPrice);
    }

    public int calculateAffordableTicketNumbers() {
        return money / LOTTO_PRICE;
    }

    public void spendOnAutoLotto(int numberOfTickets) {
        money -= (numberOfTickets * LOTTO_PRICE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money1 = (Money) o;
        return money == money1.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }
}
