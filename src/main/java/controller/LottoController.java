package controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import model.LottoGame;
import model.LottoTicket;
import model.LottoTicketDto;
import model.WinningPrize;
import view.BonusNumberInputView;
import view.LottoTicketOutputView;
import view.PurchaseMoneyInputView;
import view.RateOfReturnOutputView;
import view.WinningNumberInputView;
import view.WinningResultOutputView;

public class LottoController {

    public Integer getPurchaseMoney() {
        return (new PurchaseMoneyInputView()).printInputPurchaseMoneyAndGet();
    }

    public void printGeneratedLottoTickets(List<LottoTicket> lottoTickets) {
        List<LottoTicketDto> dto = lottoTickets.stream()
                .map(lottoTicket -> new LottoTicketDto(lottoTicket.lottoNumberValues()))
                .collect(Collectors.toList());
        (new LottoTicketOutputView()).printOutputData(dto);
    }

    public List<Integer> getWinningNumbers() {
        return (new WinningNumberInputView()).printInputWinningNumberAndGet();
    }

    public Integer getBonusNumber() {
        return (new BonusNumberInputView()).printInputBonusNumberAndGet();
    }

    public void printWinningResults(Map<WinningPrize, Integer> winningResults) {
        (new WinningResultOutputView()).printOutputData(winningResults);
    }

    public void printRateOfReturn(Double rateOfReturn) {
        (new RateOfReturnOutputView()).printOutputData(rateOfReturn);
    }
}
