package controller;

import domain.LottoMoney;
import domain.SelfPurchaseCount;
import domain.WinningTicket;
import domain.LottoResult;
import domain.strategy.RandomNumberGenerateStrategy;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import domain.strategy.DefaultWinningPrizeStrategy;
import domain.LottoGame;
import domain.dto.LottoTicketDto;
import domain.LottoTickets;
import domain.WinningPrize;
import domain.dto.WinningResultDto;
import view.InputView;
import view.OutputView;

public class LottoController {
    private final LottoGame lottoGame = new LottoGame(new RandomNumberGenerateStrategy(),
            new DefaultWinningPrizeStrategy());

    public void run() {
        LottoMoney purchaseMoney = inputPurchaseMoney();
        SelfPurchaseCount selfPurchaseCount = inputSelfTicketCount();
        LottoMoney autoPurchaseMoney = purchaseMoney.purchaseSelfTicket(selfPurchaseCount.getValue());
        LottoTickets lottoTickets = purchaseTickets(selfPurchaseCount, autoPurchaseMoney);
        showGeneratedLottoTickets(lottoTickets);
        WinningTicket winningTicket = inputWinningNumbers();
        LottoResult lottoResult = lottoGame.createWinningResult(lottoTickets, winningTicket);
        showLottoResult(lottoResult);
    }

    private LottoMoney inputPurchaseMoney() {
        try {
            return new LottoMoney(InputView.inputPurchaseMoney());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputPurchaseMoney();
        }
    }

    private SelfPurchaseCount inputSelfTicketCount() {
        try {
            return new SelfPurchaseCount(InputView.inputSelfTicketCount());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputSelfTicketCount();
        }
    }

    private LottoTickets purchaseTickets(SelfPurchaseCount selfPurchaseCount, LottoMoney autoPurchaseMoney) {
        LottoTickets selfPurchaseTickets = inputSelfLottoTicket(selfPurchaseCount);
        LottoTickets autoPurchaseTickets = lottoGame.purchaseAutoTickets(autoPurchaseMoney);
        return selfPurchaseTickets.concat(autoPurchaseTickets);
    }

    private LottoTickets inputSelfLottoTicket(SelfPurchaseCount selfPurchaseCount) {
        try {
            return LottoTickets.from(InputView.inputSelfTicketNumbers(selfPurchaseCount.getValue()));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputSelfLottoTicket(selfPurchaseCount);
        }
    }

    private void showGeneratedLottoTickets(LottoTickets lottoTickets) {
        List<LottoTicketDto> dtos = toLottoTicketDtos(lottoTickets);
        OutputView.showLottoTicket(dtos, lottoTickets.getSelfPurchaseCount());
    }

    private List<LottoTicketDto> toLottoTicketDtos(LottoTickets lottoTickets) {
        return lottoTickets.getTickets()
                .stream()
                .map(lottoTicket -> new LottoTicketDto(lottoTicket.getLottoNumberValues()))
                .collect(Collectors.toList());
    }

    private WinningTicket inputWinningNumbers() {
        try {
            Set<Integer> winningNumbers = InputView.inputWinningNumbers();
            int bonusNumber = InputView.inputBonusNumber();
            return WinningTicket.create(winningNumbers, bonusNumber);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputWinningNumbers();
        }
    }

    private void showLottoResult(LottoResult lottoResult) {
        List<WinningResultDto> winningResultDtos = toWinningResultDtos(lottoResult.getCountOfWinning());
        OutputView.showLottoResult(winningResultDtos, lottoResult.calculateLottoRateOfReturn());
    }

    private List<WinningResultDto> toWinningResultDtos(Map<WinningPrize, Integer> countOfWinning) {
        return countOfWinning.entrySet()
                .stream()
                .map(winningResult -> toWinningResultDto(winningResult.getKey(), winningResult.getValue()))
                .collect(Collectors.toList());
    }

    private WinningResultDto toWinningResultDto(WinningPrize winningPrize, int count) {
        return new WinningResultDto(lottoGame.findMatchCount(winningPrize), lottoGame.findMatchBonus(winningPrize),
                winningPrize.getPrizeMoney(), count);
    }
}
