package domain;

public class ManualLottoTicketCount {
    private static final int MIN_MANUAL_LOTTO_TICKET_COUNT = 0;

    private int manualLottoTicketCount;

    public ManualLottoTicketCount(int allLottoTicketCount, int manualLottoTicketCount) {
        validateOverZero(manualLottoTicketCount);
        validateOverAllLottoTicketCount(allLottoTicketCount, manualLottoTicketCount);
        this.manualLottoTicketCount = manualLottoTicketCount;
    }

    private void validateOverZero(int manualLottoTicketCount) {
        if (manualLottoTicketCount < MIN_MANUAL_LOTTO_TICKET_COUNT) {
            throw new IllegalArgumentException("수동으로 구매할 로또의 수는 0 이상이어야 합니다.");
        }
    }

    private void validateOverAllLottoTicketCount(int allLottoTicketCount, int manualLottoTicketCount) {
        if (allLottoTicketCount < manualLottoTicketCount) {
            throw new IllegalArgumentException("수동으로 구매할 로또의 수가 구입금액을 초과합니다.");
        }
    }
}
