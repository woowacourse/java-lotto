package controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.GenerateStrategy;
import model.LottoGame;
import model.LottoTicketDto;
import model.LottoTickets;
import model.WinningPrize;
import model.WinningPrizeStrategy;
import model.WinningResultDto;
import view.inputview.BonusNumberInputView;
import view.outputview.LottoTicketOutputView;
import view.inputview.PurchaseMoneyInputView;
import view.outputview.RateOfReturnOutputView;
import view.inputview.WinningNumberInputView;
import view.outputview.WinningResultOutputView;

public class LottoController {
    private LottoGame lottoGame;

    public void initLottoGame(LottoTickets lottoTickets,
                              List<Integer> winningNumbers,
                              int bonusNumber,
                              WinningPrizeStrategy winningPrizeStrategy) {
        lottoGame = new LottoGame(lottoTickets, winningNumbers, bonusNumber, winningPrizeStrategy);
    }

    public Map<WinningPrize, Integer> winningResults() {
        return lottoGame.winningResults();
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
        List<LottoTicketDto> dto = lottoTickets.getTickets()
                .stream()
                .map(lottoTicket -> new LottoTicketDto(lottoTicket.lottoNumberValues()))
                .collect(Collectors.toList());

        (new LottoTicketOutputView()).showOutputData(dto);
    }

    public List<Integer> inputWinningNumbers() {
        return (new WinningNumberInputView()).getUserInputData();
    }

    public Integer inputBonusNumber() {
        return (new BonusNumberInputView()).getUserInputData();
    }

    public void printWinningResults(Map<WinningPrize, Integer> winningResults) {
        List<WinningResultDto> winningResultDtos = toWinningResultDtos(winningResults);

        (new WinningResultOutputView()).showOutputData(winningResultDtos);
    }

    private List<WinningResultDto> toWinningResultDtos(Map<WinningPrize, Integer> winningResults) {
        return winningResults.entrySet()
                .stream()
                .map(countOfWinningPrize ->
                        toWinningResultDto(
                                countOfWinningPrize.getKey(),
                                countOfWinningPrize.getValue()
                        )
                ).collect(Collectors.toList());
    }

    private WinningResultDto toWinningResultDto(WinningPrize winningPrize, Integer count) {
        return new WinningResultDto
                (
                        lottoGame.matchCount(winningPrize),
                        lottoGame.matchBonus(winningPrize),
                        winningPrize.getPrizeMoney(),
                        count
                );
    }

    public void printRateOfReturn(Double rateOfReturn) {
        (new RateOfReturnOutputView()).showOutputData(rateOfReturn);
    }
}
