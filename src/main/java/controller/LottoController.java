package controller;

import domain.LottoMoney;
import domain.LottoCount;
import domain.WinningTicket;
import domain.WinningResult;
import domain.strategy.RandomNumberGenerateStrategy;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import domain.LottoMachine;
import domain.dto.LottoTicketDto;
import domain.LottoTickets;
import domain.WinningPrize;
import domain.dto.WinningResultDto;
import view.InputView;
import view.OutputView;

public class LottoController {
    private final LottoMachine lottoMachine = new LottoMachine(new RandomNumberGenerateStrategy());

    public void run() {
        LottoMoney purchaseMoney = inputPurchaseMoney();
        LottoCount selfPurchaseCount = inputSelfTicketCount();
        LottoTickets selfPurchaseTickets = inputSelfLottoTicket(selfPurchaseCount);
        LottoTickets lottoTickets = lottoMachine.purchaseTickets(purchaseMoney, selfPurchaseCount, selfPurchaseTickets);
        showGeneratedLottoTickets(lottoTickets);
        WinningTicket winningTicket = inputWinningNumbers();
        WinningResult winningResult = WinningResult.toExtract(lottoTickets, winningTicket);
        showLottoResult(winningResult, winningResult.calculateLottoRateOfReturn(purchaseMoney));
    }

    private LottoMoney inputPurchaseMoney() {
        try {
            return new LottoMoney(InputView.inputPurchaseMoney());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputPurchaseMoney();
        }
    }

    private LottoCount inputSelfTicketCount() {
        try {
            return new LottoCount(InputView.inputSelfTicketCount());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputSelfTicketCount();
        }
    }

    private LottoTickets inputSelfLottoTicket(LottoCount selfPurchaseCount) {
        try {
            List<Set<Integer>> ticketNumbers = InputView.inputSelfTicketNumbers(selfPurchaseCount.getValue());
            return LottoTickets.fromTicketNumbers(ticketNumbers);
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

    private void showLottoResult(WinningResult winningResult, double rateOfReturn) {
        List<WinningResultDto> winningResultDtos = toWinningResultDtos(winningResult.getCountOfWinning());
        OutputView.showLottoResult(winningResultDtos, rateOfReturn);
    }

    private List<WinningResultDto> toWinningResultDtos(Map<WinningPrize, Integer> countOfWinning) {
        return countOfWinning.entrySet()
                .stream()
                .map(winningResult -> toWinningResultDto(winningResult.getKey(), winningResult.getValue()))
                .collect(Collectors.toList());
    }

    private WinningResultDto toWinningResultDto(WinningPrize winningPrize, int count) {
        return new WinningResultDto(winningPrize.getMatchCount(), winningPrize.isMatchBonus(),
                winningPrize.getPrizeMoney(), count);
    }
}
