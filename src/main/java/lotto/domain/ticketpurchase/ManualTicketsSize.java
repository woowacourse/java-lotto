package lotto.domain.ticketpurchase;

public class ManualTicketsSize {
    private final int numberOfTickets;

    public ManualTicketsSize(int manualTicketsSize, PurchasePrice purchasePrice) {
        validate(manualTicketsSize, purchasePrice.allTicketsSize());
        this.numberOfTickets = manualTicketsSize;
    }

    private void validate(int manualTicketsSize, int allTicketsSize) {
        if (manualTicketsSize < 0 || allTicketsSize < manualTicketsSize) {
            throw new IllegalArgumentException("0개 이상, 총 구매 티켓 개수 이하여야 합니다.");
        }
    }

    public int size() {
        return numberOfTickets;
    }

    public boolean isNotZero() {
        return numberOfTickets != 0;
    }
}
