package lotto.domain.ticketpurchase;

import lotto.domain.exception.InvalidManualTicketsSizeException;

public class ManualTicketsSize {
    private final int numberOfTickets;

    public ManualTicketsSize(int manualTicketsSize, PurchasePrice purchasePrice) {
        validate(manualTicketsSize, purchasePrice);
        this.numberOfTickets = manualTicketsSize;
    }

    private void validate(int manualTicketsSize, PurchasePrice purchasePrice) {
        if (manualTicketsSize < 0 || purchasePrice.allTicketsSize() < manualTicketsSize) {
            throw new InvalidManualTicketsSizeException();
        }
    }

    public int size() {
        return numberOfTickets;
    }

    public boolean isNotZero() {
        return numberOfTickets != 0;
    }
}
