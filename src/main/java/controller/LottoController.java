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
import view.InputView;
import view.OutputView;

public class LottoController {
    private final InputView inputView = InputView.getInstance();
    private final OutputView outputView = OutputView.getInstance();
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

    public int inputPurchaseMoney() {
        return inputView.inputPurchaseMoney();
    }

    public void showGeneratedLottoTickets(LottoTickets lottoTickets) {
        List<LottoTicketDto> dtos = toLottoTicketDtos(lottoTickets);
        outputView.showLottoTicket(dtos);
    }

    public Set<Integer> inputWinningNumbers() {
        return inputView.inputWinningNumbers();
    }

    public int inputBonusNumber() {
        return inputView.inputBonusNumber();
    }

    public void showWinningResults(Map<WinningPrize, Integer> winningResults) {
        List<WinningResultDto> winningResultDtos = toWinningResultDtos(winningResults);
        outputView.showWinningResult(winningResultDtos);
    }

    public void showRateOfReturn(double rateOfReturn) {
        outputView.showRateOfReturn(rateOfReturn);
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

    private List<LottoTicketDto> toLottoTicketDtos(LottoTickets lottoTickets) {
        return lottoTickets.getTickets()
                .stream()
                .map(lottoTicket -> new LottoTicketDto(lottoTicket.lottoNumberValues()))
                .collect(Collectors.toList());
    }
}
