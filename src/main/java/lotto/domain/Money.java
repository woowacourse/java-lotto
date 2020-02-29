package lotto.domain;

import java.util.Objects;

import static lotto.domain.ticket.LottoTicket.LOTTO_PRICE;

public class Money {
    private static final String MESSAGE_FOR_NOT_ENOUGH_MONEY = "%d는 최소 구매 금액보다 작습니다.";
    private static final String MESSAGE_FOR_CANNOT_AFFORD = "구매 가능한 로또 수: %d, 구매 로또 수: %d - 잔액이 부족합니다.";

    private int numberOfManualTickets;
    private int numberOfLeftTickets;


    public Money(int amount, int numberOfManualTickets) {
        validateMoney(amount);

        numberOfLeftTickets = calculateAffordableTicketNumbers(amount);

        validateTicketNumber(numberOfManualTickets);
        this.numberOfManualTickets = numberOfManualTickets;
        this.numberOfLeftTickets -= numberOfManualTickets;
    }

    private void validateMoney(int bettingMoney) {
        if (bettingMoney < LOTTO_PRICE) {
            throw new IllegalArgumentException(String.format(MESSAGE_FOR_NOT_ENOUGH_MONEY, bettingMoney));
        }
    }

    private int calculateAffordableTicketNumbers(int money) {
        return money / LOTTO_PRICE;
    }

    private void validateTicketNumber(int numberOfTickets) {
        if (!canAfford(numberOfTickets)) {
            throw new IllegalArgumentException(String.format(MESSAGE_FOR_CANNOT_AFFORD, numberOfLeftTickets, numberOfTickets));
        }
    }

    private boolean canAfford(int ticketNumber) {
        return !(numberOfLeftTickets < ticketNumber);
    }

    public int getNumberOfLeftTickets() {
        return numberOfLeftTickets;
    }

    public int getNumberOfManualTickets() {
        return numberOfManualTickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return numberOfLeftTickets == money.numberOfLeftTickets &&
                numberOfManualTickets == money.numberOfManualTickets;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfLeftTickets, numberOfManualTickets);
    }
}
