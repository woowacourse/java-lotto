package controller;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import model.DefaultLottoWinningPrizeStrategy;
import model.GenerateStrategy;
import model.LottoGame;
import model.LottoNumberGenerateStrategy;
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

    public void run() {
        LottoTickets lottoTickets = purchaseLottoTickets();
        showGeneratedLottoTickets(lottoTickets);
        settingLottoGame(lottoTickets);
        Map<WinningPrize, Integer> winningResults = lottoGame.winningResults();
        showWinningResults(winningResults);
        double rateOfReturn = lottoGame.getLottoRateOfReturn();
        outputView.showRateOfReturn(rateOfReturn);
    }

    private void settingLottoGame(LottoTickets lottoTickets) {
        Set<Integer> winningNumbers = inputView.inputWinningNumbers();
        int bonusNumber = inputView.inputBonusNumber();
        initLottoGame(lottoTickets, winningNumbers, bonusNumber, new DefaultLottoWinningPrizeStrategy());
    }

    private LottoTickets purchaseLottoTickets() {
        try {
            int purchaseMoney = inputView.inputPurchaseMoney();
            return new LottoTickets(purchaseMoney, new LottoNumberGenerateStrategy());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return purchaseLottoTickets();
        }
    }

    private void initLottoGame(LottoTickets lottoTickets,
                              Set<Integer> winningNumbers,
                              int bonusNumber,
                              WinningPrizeStrategy winningPrizeStrategy) {
        lottoGame = new LottoGame(lottoTickets, winningNumbers, bonusNumber, winningPrizeStrategy);
    }

    private void showGeneratedLottoTickets(LottoTickets lottoTickets) {
        List<LottoTicketDto> dtos = toLottoTicketDtos(lottoTickets);
        outputView.showLottoTicket(dtos);
    }

    private void showWinningResults(Map<WinningPrize, Integer> winningResults) {
        List<WinningResultDto> winningResultDtos = toWinningResultDtos(winningResults);
        outputView.showWinningResult(winningResultDtos);
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
