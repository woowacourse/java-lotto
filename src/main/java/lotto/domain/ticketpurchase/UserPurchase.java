package lotto.domain.ticketpurchase;

public class UserPurchase {
    private final PurchasePrice purchasePrice;
    private final ManualTicketsSize manualTicketsSize;

    public UserPurchase(PurchasePrice purchasePrice, ManualTicketsSize manualTicketsSize) {
        this.purchasePrice = purchasePrice;
        this.manualTicketsSize = manualTicketsSize;
    }

    public PurchasePrice getPurchasePrice() {
        return purchasePrice;
    }

    public int manualTicketsSize() {
        return manualTicketsSize.size();
    }

    public int randomTicketsSize() {
        return allTicketsSize() - manualTicketsSize.size();
    }

    public int allTicketsSize() {
        return purchasePrice.allTicketsSize();
    }

    public boolean isPurchaseManually() {
        return manualTicketsSize.isNotZero();
    }

    public boolean isPurchaseRandomly() {
        return purchasePrice.allTicketsSize() - manualTicketsSize() > 0;
    }
}
