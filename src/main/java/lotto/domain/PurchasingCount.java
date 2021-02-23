package lotto.domain;

public class PurchasingCount {
    private final int manualTicketCounts;
    private final int autoTicketCounts;

    public PurchasingCount(int manualTicketCounts, int autoTicketCounts) {
        validateTicketCounts(manualTicketCounts, autoTicketCounts);
        this.manualTicketCounts = manualTicketCounts;
        this.autoTicketCounts = autoTicketCounts;
    }

    private void validateTicketCounts(int manualTicketCounts, int autoTicketCounts) {
        if (manualTicketCounts < 0 || autoTicketCounts < 0) {
            throw new IllegalArgumentException("구매 가능한 티켓 개수 범위를 벗어났습니다.");
        }
    }

    public static PurchasingCount of(PurchasingPrice purchasingPrice, int manualTicketCounts) {
        int allTicketCounts = purchasingPrice.calculatePurchasableTicketCounts();
        return new PurchasingCount(manualTicketCounts, allTicketCounts - manualTicketCounts);
    }

    public int getManualTicketCounts() {
        return manualTicketCounts;
    }

    public int getAutoTicketCounts() {
        return autoTicketCounts;
    }
}
