package controller;

import domain.WinningResult;
import domain.WinningTicket;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import domain.LottoWinningPrizeStrategy;
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
    private final WinningPrizeStrategy winningPrizeStrategy = new LottoWinningPrizeStrategy();

    public void run() {
        LottoTickets lottoTickets = purchaseLottoTickets();
        showGeneratedLottoTickets(lottoTickets);
        LottoGame lottoGame = initLottoGame(lottoTickets);
        WinningResult winningResult = lottoGame.getWinningResult();
        showWinningResults(winningResult);
        double rateOfReturn = lottoGame.getLottoRateOfReturn();
        outputView.showRateOfReturn(rateOfReturn);
    }

    private LottoGame initLottoGame(LottoTickets lottoTickets) {
        try {
            Set<Integer> winningNumbers = inputView.inputWinningNumbers();
            int bonusNumber = inputView.inputBonusNumber();
            WinningTicket winningTicket = new WinningTicket(winningNumbers, bonusNumber);
            return new LottoGame(lottoTickets, winningTicket, winningPrizeStrategy);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return initLottoGame(lottoTickets);
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

    private void showWinningResults(WinningResult winningResult) {
        List<WinningResultDto> winningResultDtos = toWinningResultDtos(winningResult.getCountOfWinning());
        outputView.showWinningResult(winningResultDtos);
    }

    private List<WinningResultDto> toWinningResultDtos(Map<WinningPrize, Integer> countOfWinning) {
        return countOfWinning.entrySet()
                .stream()
                .map(winningResult -> toWinningResultDto(winningResult.getKey(), winningResult.getValue()))
                .collect(Collectors.toList());
    }

    private WinningResultDto toWinningResultDto(WinningPrize winningPrize, Integer count) {
        return new WinningResultDto
                (
                        winningPrizeStrategy.findMatchCount(winningPrize),
                        winningPrizeStrategy.findMatchBonus(winningPrize),
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
