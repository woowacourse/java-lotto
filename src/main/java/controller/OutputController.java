package controller;

import java.util.List;
import java.util.Map;

import model.lottotickets.LottoTicketDto;
import model.winning.Rank;
import view.LottoTicketOutputView;
import view.RateOfReturnOutputView;
import view.WinningResultOutputView;

public class OutputController {

    public void printPurchasedLottoTickets(final List<LottoTicketDto> dto) {
        (new LottoTicketOutputView()).printOutputData(dto);
    }

    public void printWinningResults(final Map<Rank, Integer> result) {
        (new WinningResultOutputView()).printOutputData(result);
    }

    public void printRateOfReturn(final Double rateOfReturn) {
        (new RateOfReturnOutputView()).printOutputData(rateOfReturn);
    }
}
