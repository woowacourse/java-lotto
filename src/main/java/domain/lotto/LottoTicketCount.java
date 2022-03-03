package domain.lotto;

public class LottoTicketCount {
    public static final String LOTTO_TICKET_COUNT_LESS_ZERO_ERROR_MESSAGE = "로또티켓 수는 1 이상이어야 합니다.";
    public static final String LOTTO_TICKET_COUNT_MAX_ERROR_MESSAGE = "수동로또 수가 구매한 로또 수를 초과할 수 없습니다.";
    private final int manualTicketCount;
    private final int autoTicketCount;

    private LottoTicketCount(final int manualTicketCount, final int autoTicketCount) {
        validate(manualTicketCount, autoTicketCount);
        this.manualTicketCount = manualTicketCount;
        this.autoTicketCount = autoTicketCount;
    }

    public static LottoTicketCount of(final int fullTicketCount, final int manualTicketCount) {
        return new LottoTicketCount(manualTicketCount, fullTicketCount - manualTicketCount);
    }

    private static void validate(final int manualTicketCount, final int autoTicketCount) {
        if (autoTicketCount < 0) {
            throw new IllegalArgumentException(LOTTO_TICKET_COUNT_MAX_ERROR_MESSAGE);
        }
        if (manualTicketCount < 0  || manualTicketCount + autoTicketCount <= 0) {
            throw new IllegalArgumentException(LOTTO_TICKET_COUNT_LESS_ZERO_ERROR_MESSAGE);
        }
    }

    public int getManualCount() {
        return manualTicketCount;
    }

    public int getAutoCount() {
        return autoTicketCount;
    }
}