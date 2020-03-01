package lotto.domain;

import lotto.utils.ValidationUtils;

public class LottoTicketCount {

    private int ticketCount;

    public LottoTicketCount(String ticketCount) {
        ValidationUtils.validateIntegerNumberFormat(ticketCount);
        ValidationUtils.validatePositiveNumber(ticketCount);

        this.ticketCount = Integer.parseInt(ticketCount);
    }

    public void validateOverTicketCount(LottoTicketCount allTicketLottoTicketCount) {
        if (this.ticketCount > allTicketLottoTicketCount.ticketCount) {
            throw new IllegalArgumentException("구매 할 수 있는 티켓을 초과했습니다. 재입력 해주세요.");
        }
    }

    public int getTicketCount() {
        return this.ticketCount;
    }

    public void calculateAutoTicketCount(LottoTicketCount manualTicketLottoTicketCount) {
        this.ticketCount -= manualTicketLottoTicketCount.getTicketCount();
    }
}
