package lotto.domain;

import lotto.utils.StringUtils;
import lotto.utils.ValidationUtils;

public class LottoTicketCount {

    private static final String OVER_VALIDATE_INPUT = "구매 할 수 있는 티켓을 초과했습니다. 재입력 해주세요.";
    private int ticketCount;

    public LottoTicketCount(String ticketCount) {
        validateLottoTicketCount(ticketCount);

        this.ticketCount = StringUtils.stringToInt(ticketCount);
    }

    private void validateLottoTicketCount(String ticketCount) {
        ValidationUtils.validateIntegerNumberFormat(ticketCount);
        ValidationUtils.validatePositiveNumber(ticketCount);
    }

    public void validateOverTicketCount(LottoTicketCount allTicketLottoTicketCount) {
        if (this.ticketCount > allTicketLottoTicketCount.ticketCount) {
            throw new IllegalArgumentException(OVER_VALIDATE_INPUT);
        }
    }

    public int getTicketCount() {
        return this.ticketCount;
    }

    public void calculateAutoTicketCount(LottoTicketCount manualTicketLottoTicketCount) {
        this.ticketCount -= manualTicketLottoTicketCount.getTicketCount();
    }
}
