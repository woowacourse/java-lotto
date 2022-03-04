package domain;

public class LottoOrder {

    private final Payment payment;
    private final int manualTicketCount;
    private final int autoTicketCount;
    
    public LottoOrder(Payment payment, int manualTicketCount, int autoTicketCount) {
        validateLottoOrder(payment.calculateLottoCount(), manualTicketCount, autoTicketCount);
        this.payment = payment;
        this.manualTicketCount = manualTicketCount;
        this.autoTicketCount = autoTicketCount;
    }

    private static void validateLottoOrder(int totalLottoTicketCount, int manualTicketCount, int autoTicketCount) {
        validateTicketCount(manualTicketCount);
        validateTicketCount(autoTicketCount);
        validateManualTicketCount(totalLottoTicketCount, manualTicketCount);
        validateTotalTicketCount(totalLottoTicketCount, manualTicketCount, autoTicketCount);
    }

    private static void validateTicketCount(int ticketCount) {
        if (ticketCount < 0) {
            throw new IllegalArgumentException("로또 티켓 수는 0 이상 이어야 합니다.");
        }
    }

    private static void validateManualTicketCount(int totalLottoTicketCount, int manualTicketCount) {
        if (totalLottoTicketCount < manualTicketCount) {
            throw new IllegalArgumentException(String.format("수동으로 구입 가능한 티켓 수는 %d보다 작아야 합니다.",
                    totalLottoTicketCount));
        }
    }

    private static void validateTotalTicketCount(int totalLottoTicketCount,
                                                 int manualTicketCount,
                                                 int autoTicketCount) {
        if (totalLottoTicketCount != manualTicketCount + autoTicketCount) {
            throw new IllegalArgumentException("수동 티켓 수와 자동 티켓 수의 합이 총 구매 가능한 티켓수와 다릅니다.");
        }
    }

    public int getManualTicketCount() {
        return manualTicketCount;
    }

    public int getAutoTicketCount() {
        return autoTicketCount;
    }
}
