package lotto.domain.ticketpurchase;

public class UserPurchase {
    private static final int ONE_TICKET_PRICE = 1000;
    private static final int ZERO = 0;

    private final int purchasePrice;
    private final int manualTicketNumber;
    private final int autoTicketNumber;

    public UserPurchase(int purchasePrice, int manualTicketNumber) {
        validateExactlyDividedByOneTicketPrice(purchasePrice);
        validateManualTicketPriceNotGreaterThanPurchasePrice(purchasePrice, manualTicketNumber);
        this.purchasePrice = purchasePrice;
        this.manualTicketNumber = manualTicketNumber;
        this.autoTicketNumber = (purchasePrice / ONE_TICKET_PRICE) - manualTicketNumber;
    }

    private void validateExactlyDividedByOneTicketPrice(int purchasePrice) {
        if (isExactlyDividedByThousand(purchasePrice)) {
            throw new IllegalArgumentException("구입 금액은 1000원 단위여야 합니다.");
        }
    }

    private boolean isExactlyDividedByThousand(int purchasePrice) {
        return purchasePrice <= ZERO || purchasePrice % ONE_TICKET_PRICE != ZERO;
    }

    private void validateManualTicketPriceNotGreaterThanPurchasePrice(int purchasePrice, int manualTicketNumber) {
        if (isManualTicketPriceOverPurchasePrice(purchasePrice, manualTicketNumber)) {
            throw new IllegalArgumentException("구입금액에 맞게 입력해 주세요");
        }
    }

    private boolean isManualTicketPriceOverPurchasePrice(int purchasePrice, int manualTicketNumber) {
        return purchasePrice < (manualTicketNumber * ONE_TICKET_PRICE);
    }

    public int getPurchasePrice() {
        return this.purchasePrice;
    }

    public int getManualTicketCount() {
        return this.manualTicketNumber;
    }

    public int getAutoTicketCount() {
        return this.autoTicketNumber;
    }
}
