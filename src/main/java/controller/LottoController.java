package controller;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import domain.DefaultLottoWinningPrizeStrategy;
import domain.LottoGame;
import domain.LottoNumberGenerateStrategy;
import domain.LottoTicketDto;
import domain.LottoTickets;
import domain.WinningPrize;
import domain.WinningPrizeStrategy;
import domain.WinningResultDto;
import view.InputView;
import view.OutputView;

public class LottoController {
    private final InputView inputView = InputView.getInstance();
    private final OutputView outputView = OutputView.getInstance();
    private LottoGame lottoGame;

    public void run() {
        LottoTickets lottoTickets = purchaseLottoTickets();
        showGeneratedLottoTickets(lottoTickets);
        initLottoGame(lottoTickets);
        Map<WinningPrize, Integer> winningResults = lottoGame.getWinningResults();
        showWinningResults(winningResults);
        double rateOfReturn = lottoGame.getLottoRateOfReturn();
        outputView.showRateOfReturn(rateOfReturn);
    }

    private void initLottoGame(LottoTickets lottoTickets) {
        try {
            Set<Integer> winningNumbers = inputView.inputWinningNumbers();
            int bonusNumber = inputView.inputBonusNumber();
            WinningPrizeStrategy winningPrizeStrategy = new DefaultLottoWinningPrizeStrategy();
            lottoGame = new LottoGame(lottoTickets, winningNumbers, bonusNumber, winningPrizeStrategy);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            initLottoGame(lottoTickets);
        }
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
                .map(winningResult -> toWinningResultDto(winningResult.getKey(), winningResult.getValue()))
                .collect(Collectors.toList());
    }

    private WinningResultDto toWinningResultDto(WinningPrize winningPrize, Integer count) {
        return new WinningResultDto
                (
                        lottoGame.findMatchCount(winningPrize),
                        lottoGame.findMatchBonus(winningPrize),
                        winningPrize.getPrizeMoney(),
                        count
                );
    }

    private List<LottoTicketDto> toLottoTicketDtos(LottoTickets lottoTickets) {
        return lottoTickets.getTickets()
                .stream()
                .map(lottoTicket -> new LottoTicketDto(lottoTicket.getLottoNumberValues()))
                .collect(Collectors.toList());
    }
}
