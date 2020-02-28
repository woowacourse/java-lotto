package lotto.domain.LottoTicketNumber;

public class AutomaticLottoTicketNumber extends LottoTicketNumber {
    public AutomaticLottoTicketNumber(int lottoTicketNumber, int totalLottoTicketNumber) {
        super(totalLottoTicketNumber - lottoTicketNumber, totalLottoTicketNumber);
    }
}
