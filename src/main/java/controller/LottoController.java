package controller;

import domain.strategy.GenerateStrategy;
import domain.LottoTicket;
import domain.WinningResult;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import domain.strategy.LottoWinningPrizeStrategy;
import domain.LottoGame;
import domain.strategy.LottoNumberGenerateStrategy;
import domain.dto.LottoTicketDto;
import domain.LottoTickets;
import domain.WinningPrize;
import domain.strategy.WinningPrizeStrategy;
import domain.dto.WinningResultDto;
import view.InputView;
import view.OutputView;

public class LottoController {
    private final InputView inputView = InputView.getInstance();
    private final OutputView outputView = OutputView.getInstance();
    private final WinningPrizeStrategy winningPrizeStrategy = new LottoWinningPrizeStrategy();
    private final GenerateStrategy generateStrategy = new LottoNumberGenerateStrategy();
    private final LottoGame lottoGame = new LottoGame(winningPrizeStrategy);

    public void run() {
        purchaseLottoTickets();
        showGeneratedLottoTickets();
        inputWinningNumbers();
        showLottoResult();
    }

    private void inputWinningNumbers() {
        try {
            Set<Integer> winningNumbers = inputView.inputWinningNumbers();
            int bonusNumber = inputView.inputBonusNumber();
            lottoGame.inputWinningNumbers(winningNumbers, bonusNumber);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            inputWinningNumbers();
        }
    }

    private void purchaseLottoTickets() {
        try {
            int purchaseMoney = inputView.inputPurchaseMoney();
            int selfPurchaseCount = inputView.inputSelfTicketCount();
            purchaseMoney -= selfPurchaseCount * LottoTicket.TICKET_PRICE;
            List<Set<Integer>> selfTicketNumbers = inputView.inputSelfTicketNumbers(selfPurchaseCount);
            lottoGame.purchaseLottoTickets(selfTicketNumbers, purchaseMoney, generateStrategy);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            purchaseLottoTickets();
        }
    }

    private void showGeneratedLottoTickets() {
        LottoTickets lottoTickets = lottoGame.getLottoTickets();
        List<LottoTicketDto> dtos = toLottoTicketDtos(lottoTickets);
        outputView.showLottoTicket(dtos, lottoTickets.getSelfPurchaseCount());
    }

    private void showLottoResult() {
        WinningResult winningResult = lottoGame.createWinningResult();
        List<WinningResultDto> winningResultDtos = toWinningResultDtos(winningResult.getCountOfWinning());
        outputView.showLottoResult(winningResultDtos, lottoGame.calculateLottoRateOfReturn());
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
