package lotto.domain.ticketpurchase;

public class UserPurchase {
    private final PurchasePrice purchasePrice;
    private final NumberOfTicketsToPurchaseManually numberOfTicketsToPurchaseManually;

    public UserPurchase(PurchasePrice purchasePrice,
        NumberOfTicketsToPurchaseManually numberOfTicketsToPurchaseManually) {

        this.purchasePrice = purchasePrice;
        this.numberOfTicketsToPurchaseManually = numberOfTicketsToPurchaseManually;
    }

    public int getNumberOfAllTickets() {
        return purchasePrice.getNumberOfAllTicketsToPurchase();
    }

    public int autoTicketsSize() {
        return getNumberOfAllTickets() - numberOfTicketsToPurchaseManually.size();
    }

    public PurchasePrice getPurchasePrice() {
        return purchasePrice;
    }

    public int manualTicketsSize() {
        return numberOfTicketsToPurchaseManually.size();
    }

    public boolean isPurchaseManual() {
         return numberOfTicketsToPurchaseManually.isNotZero();
    }

    public boolean isPurchaseAuto() {
        return false;
    }
}
