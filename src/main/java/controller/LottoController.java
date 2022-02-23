package controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import model.GenerateStrategy;
import model.LottoGame;
import model.LottoTicketDto;
import model.LottoTickets;
import model.WinningPrize;
import view.BonusNumberInputView;
import view.LottoTicketOutputView;
import view.PurchaseMoneyInputView;
import view.RateOfReturnOutputView;
import view.WinningNumberInputView;
import view.WinningResultOutputView;

public class LottoController {
    private LottoGame lottoGame;

    public void initLottoGame(LottoTickets lottoTickets, List<Integer> winningNumbers, int bonusNumber) {
        lottoGame = new LottoGame(lottoTickets, winningNumbers, bonusNumber);
    }

    public Map<WinningPrize, Integer> winningResults() {
        return lottoGame.winningResult();
    }

    public Double rateOfReturn() {
        return lottoGame.getLottoRateOfReturn();
    }

    public LottoTickets createLottoTickets(int purchaseMoney, GenerateStrategy generateStrategy) {
        return new LottoTickets(purchaseMoney, generateStrategy);
    }

    public Integer inputPurchaseMoney() {
        return (new PurchaseMoneyInputView()).getUserInputData();
    }

    public void printGeneratedLottoTickets(LottoTickets lottoTickets) {
        List<LottoTicketDto> dto = lottoTickets.getTickets().stream()
                .map(lottoTicket -> new LottoTicketDto(lottoTicket.lottoNumberValues()))
                .collect(Collectors.toList());
        (new LottoTicketOutputView()).printOutputData(dto);
    }

    public List<Integer> inputWinningNumbers() {
        return (new WinningNumberInputView()).getUserInputData();
    }

    public Integer inputBonusNumber() {
        return (new BonusNumberInputView()).getUserInputData();
    }

    public void printWinningResults(Map<WinningPrize, Integer> winningResults) {
        (new WinningResultOutputView()).printOutputData(winningResults);
    }

    public void printRateOfReturn(Double rateOfReturn) {
        (new RateOfReturnOutputView()).printOutputData(rateOfReturn);
    }
}
