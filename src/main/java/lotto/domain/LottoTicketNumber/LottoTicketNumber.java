package lotto.domain.LottoTicketNumber;

import lotto.Exception.NumberOutOfRangeException;

public class LottoTicketNumber {
    public static final String OUT_OF_LOTTO_TICKET_NUMBER_ERROR_MESSAGE = "구매할 수 있는 수량보다 더 크게 입력하였습니다.";

    private int lottoTicketNumber;

    public LottoTicketNumber(int lottoTicketNumber, int totalLottoTicketNumber) {
        this.lottoTicketNumber = lottoTicketNumber;
        ValidateOutOfTotalLottoTicket(totalLottoTicketNumber);
    }

    private void ValidateOutOfTotalLottoTicket(int totalLottoTicketNumber){
        if(totalLottoTicketNumber < this.lottoTicketNumber){
            throw new NumberOutOfRangeException(OUT_OF_LOTTO_TICKET_NUMBER_ERROR_MESSAGE);
        }
    }

    public int getLottoTicketNumber() {
        return lottoTicketNumber;
    }
}
