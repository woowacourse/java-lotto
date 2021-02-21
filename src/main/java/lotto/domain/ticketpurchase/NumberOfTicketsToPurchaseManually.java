package lotto.domain.ticketpurchase;

public class NumberOfTicketsToPurchaseManually {
    private final int numberOfTickets;

    public NumberOfTicketsToPurchaseManually(int numberOfTicketsToPurchaseManually,
        PurchasePrice purchasePrice) {
        validateZeroOrMoreAndNumberOfTicketsOrLess(numberOfTicketsToPurchaseManually,
            purchasePrice.getNumberOfAllTicketsToPurchase());
        this.numberOfTickets = numberOfTicketsToPurchaseManually;
    }

    private void validateZeroOrMoreAndNumberOfTicketsOrLess(int numberOfTicketsToPurchaseManually,
        int numberOfAllTicketsToPurchase) {
        if (numberOfTicketsToPurchaseManually < 0
            || numberOfAllTicketsToPurchase < numberOfTicketsToPurchaseManually) {
            throw new IllegalArgumentException("0개 이상, 총 구매 티켓 개수 이하여야 합니다.");
        }
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }
}
