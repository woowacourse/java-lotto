package lotto.domain;

import lotto.Exception.NumberOutOfRangeException;

public class LottoTicketNumber {
    public static final String OUT_OF_LOTTO_TICKET_NUMBER_ERROR_MESSAGE = "구매할 수 있는 수량보다 더 크게 입력하였습니다.";

    private int totalLottoTicketNumber;
    private int manualLottoTicketNumber;
    private int automaticLottoTicketNumber;

    public LottoTicketNumber(int totalLottoTicketNumber, int manualLottoTicketNumber) {
        this.manualLottoTicketNumber = manualLottoTicketNumber;
        this.totalLottoTicketNumber = totalLottoTicketNumber;
        this.automaticLottoTicketNumber = totalLottoTicketNumber - manualLottoTicketNumber;
        ValidateOutOfTotalLottoTicket();
    }

    private void ValidateOutOfTotalLottoTicket(){
        if(this.totalLottoTicketNumber < this.manualLottoTicketNumber){
            throw new NumberOutOfRangeException(OUT_OF_LOTTO_TICKET_NUMBER_ERROR_MESSAGE);
        }
    }
}
