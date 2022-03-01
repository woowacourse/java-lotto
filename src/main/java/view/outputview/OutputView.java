package view.outputview;

import java.util.List;
import model.LottoTicketDto;
import model.WinningResultDto;

public interface OutputView {
    void showLottoTicket(List<LottoTicketDto> lottoTickets);
    void showWinningResult(List<WinningResultDto> winningResults);
    void showRateOfReturn(double rateOfReturn);
}
