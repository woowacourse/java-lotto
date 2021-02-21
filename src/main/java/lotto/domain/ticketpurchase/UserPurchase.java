package lotto.domain.ticketpurchase;

public class UserPurchase {
    private static final int ONE_TICKET_PRICE = 1000;

    private final int purchasePrice;
    private final LottoTickets manuallyPurchasedLottoTickets;
    private final int numberOfAllTickets;

    public UserPurchase(int purchasePrice, LottoTickets manuallyPurchasedLottoTickets) {
        validateExactlyDividedByOneTicketPrice(purchasePrice);
        this.purchasePrice = purchasePrice;
        this.numberOfAllTickets = purchasePrice / ONE_TICKET_PRICE;
        validateZeroOrMoreAndNumberOfTicketsOrLess(manuallyPurchasedLottoTickets.size());
        this.manuallyPurchasedLottoTickets = manuallyPurchasedLottoTickets;
    }

    private void validateExactlyDividedByOneTicketPrice(int purchasePrice) {
        if (purchasePrice <= 0 || (purchasePrice % ONE_TICKET_PRICE) != 0) {
            throw new IllegalArgumentException("구입 금액은 0보다 큰 1000원 단위여야 합니다.");
        }
    }

    private void validateZeroOrMoreAndNumberOfTicketsOrLess(int sizeOfManuallyPurchasedLottoTickets) {
        if (sizeOfManuallyPurchasedLottoTickets < 0
            || numberOfAllTickets < sizeOfManuallyPurchasedLottoTickets) {
            throw new IllegalArgumentException("0개 이상, 총 구매 티켓 개수 이하여야 합니다.");
        }
    }

    public int getPurchasePrice() {
        return this.purchasePrice;
    }

    public int getNumberOfAllTickets() {
        return this.numberOfAllTickets;
    }

    public int getNumberOfManuallyPurchasedTickets() {
        return manuallyPurchasedLottoTickets.size();
    }

    public int getNumberOfAutomaticallyPurchasedTickets() {
        return numberOfAllTickets - manuallyPurchasedLottoTickets.size();
    }
}
