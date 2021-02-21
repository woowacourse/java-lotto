package lotto.domain.ticketpurchase;

public class UserPurchase {
    private final PurchasePrice purchasePrice;
    private final LottoTickets manuallyPurchasedLottoTickets;

    public UserPurchase(PurchasePrice purchasePrice, LottoTickets manuallyPurchasedLottoTickets) {
        this.purchasePrice = purchasePrice;
        this.manuallyPurchasedLottoTickets = manuallyPurchasedLottoTickets;
    }

    public UserPurchase(PurchasePrice purchasePrice) {
        this(purchasePrice, new LottoTickets());
    }

    public int getNumberOfAllTickets() {
        return purchasePrice.getNumberOfAllTicketsToPurchase();
    }

    public LottoTickets getManuallyPurchasedLottoTickets() {
        return manuallyPurchasedLottoTickets;
    }
    public int getNumberOfAutomaticallyPurchasedLottoTickets() {
        return getNumberOfAllTickets() - manuallyPurchasedLottoTickets.size();
    }

    public PurchasePrice getPurchasePrice() {
        return purchasePrice;
    }
}
