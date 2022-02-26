package controller;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import model.GenerateStrategy;
import model.LottoGame;
import model.LottoTicketDto;
import model.LottoTickets;
import model.WinningPrize;
import model.WinningPrizeStrategy;
import model.WinningResultDto;

public class LottoController {
    private final ViewContainer viewContainer = new ViewContainer();
    private LottoGame lottoGame;

    public void initLottoGame(LottoTickets lottoTickets,
                              Set<Integer> winningNumbers,
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
        return viewContainer.getPurchaseMoneyInputView().getUserInputData();
    }

    public void printGeneratedLottoTickets(LottoTickets lottoTickets) {
        List<LottoTicketDto> dto = lottoTickets.getTickets()
                .stream()
                .map(lottoTicket -> new LottoTicketDto(lottoTicket.lottoNumberValues()))
                .collect(Collectors.toList());

        viewContainer.getLottoTicketOutputView().showOutputData(dto);
    }

    public Set<Integer> inputWinningNumbers() {
        return viewContainer.getWinningNumberInputView().getUserInputData();
    }

    public Integer inputBonusNumber() {
        return viewContainer.getBonusNumberInputView().getUserInputData();
    }

    public void printWinningResults(Map<WinningPrize, Integer> winningResults) {
        List<WinningResultDto> winningResultDtos = toWinningResultDtos(winningResults);
        viewContainer.getWinningResultOutputView().showOutputData(winningResultDtos);
    }


    public void printRateOfReturn(Double rateOfReturn) {
        viewContainer.getRateOfReturnOutputView().showOutputData(rateOfReturn);
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
}
