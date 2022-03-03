package controller;

import domain.LottoMoney;
import domain.SelfPurchaseCount;
import domain.strategy.NumberGenerateStrategy;
import domain.LottoTicket;
import domain.LottoResult;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import domain.strategy.DefaultWinningPrizeStrategy;
import domain.LottoGame;
import domain.strategy.RandomNumberGenerateStrategy;
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
    private final WinningPrizeStrategy winningPrizeStrategy = new DefaultWinningPrizeStrategy();
    private final NumberGenerateStrategy generateStrategy = new RandomNumberGenerateStrategy();
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
            LottoMoney purchaseMoney = new LottoMoney(inputView.inputPurchaseMoney());
            SelfPurchaseCount selfPurchaseCount = new SelfPurchaseCount(inputView.inputSelfTicketCount());
            purchaseMoney.purchaseSelfTicket(selfPurchaseCount.getValue());
            List<Set<Integer>> selfTicketNumbers = inputView.inputSelfTicketNumbers(selfPurchaseCount.getValue());
            lottoGame.purchaseLottoTickets(selfTicketNumbers, purchaseMoney.getAmount(), generateStrategy);
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
        LottoResult lottoResult = lottoGame.createWinningResult();
        List<WinningResultDto> winningResultDtos = toWinningResultDtos(lottoResult.getCountOfWinning());
        outputView.showLottoResult(winningResultDtos, lottoResult.calculateLottoRateOfReturn());
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
