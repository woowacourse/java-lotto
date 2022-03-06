package controller;

import domain.LottoCount;
import domain.LottoMachine;
import domain.LottoMoney;
import domain.LottoTickets;
import domain.WinningPrize;
import domain.WinningResult;
import domain.WinningTicket;
import domain.dto.LottoTicketDto;
import domain.dto.WinningResultDto;
import domain.strategy.RandomNumberGenerateStrategy;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import view.InputView;
import view.OutputView;

public class LottoController {

    public void run() {
        LottoMachine lottoMachine = new LottoMachine(new RandomNumberGenerateStrategy());
        LottoMoney purchaseMoney = inputPurchaseMoney();
        LottoTickets lottoTickets = purchaseTickets(lottoMachine, purchaseMoney);
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

    private LottoTickets purchaseTickets(LottoMachine lottoMachine, LottoMoney purchaseMoney) {
        LottoCount selfPurchaseCount = inputSelfTicketCount();
        LottoTickets selfPurchaseTickets = inputSelfLottoTicket(selfPurchaseCount);
        return lottoMachine.purchaseTickets(purchaseMoney, selfPurchaseCount, selfPurchaseTickets);
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
