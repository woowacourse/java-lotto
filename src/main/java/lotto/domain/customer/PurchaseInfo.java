package lotto.domain.customer;

import java.util.Objects;

import static lotto.domain.ticket.LottoTicket.LOTTO_PRICE;

public class PurchaseInfo {
    private static final String MESSAGE_FOR_NOT_ENOUGH_MONEY = "%d는 최소 구매 금액보다 작습니다.";
    private static final String MESSAGE_FOR_CANNOT_AFFORD = "구매 가능한 로또 수: %d, 구매 로또 수: %d - 잔액이 부족합니다.";

    private int numberOfManualTickets;
    private int numberOfLeftTickets;

    public PurchaseInfo(int money, int numberOfManualTickets) {
        validateMoney(money);
        numberOfLeftTickets = calculateAffordableTicketNumbers(money);

        validateTicketNumber(numberOfManualTickets);
        this.numberOfManualTickets = numberOfManualTickets;
        this.numberOfLeftTickets -= numberOfManualTickets;
    }

    private void validateMoney(int money) {
        if (money < LOTTO_PRICE) {
            throw new IllegalArgumentException(String.format(MESSAGE_FOR_NOT_ENOUGH_MONEY, money));
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
        PurchaseInfo purchaseInfo = (PurchaseInfo) o;
        return numberOfLeftTickets == purchaseInfo.numberOfLeftTickets &&
                numberOfManualTickets == purchaseInfo.numberOfManualTickets;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfLeftTickets, numberOfManualTickets);
    }
}
