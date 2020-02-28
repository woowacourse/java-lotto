package controller;

import domain.*;
import view.InputViewer;
import view.OutputViewer;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoController {
    private LottoController() {
    }

    public static Money getMoney(int inputMoney) {
        try {
            Money money = new Money(inputMoney);
            return money;
        } catch (IllegalArgumentException e) {
            OutputViewer.printErrorMessage(e.getMessage());
            return getMoney(InputViewer.inputMoney());
        }
    }

    public static WinningLottoTicket getWinningLottoTicket(String winningLottoNumbers) {
        try {
            WinningLottoTicket winningLottoTicket = new WinningLottoTicket(winningLottoNumbers);
            return winningLottoTicket;
        } catch (IllegalArgumentException e) {
            OutputViewer.printErrorMessage(e.getMessage());
            return getWinningLottoTicket(InputViewer.inputWinningLottoTicketNumber());
        }
    }

    public static void getBonusBall(WinningLottoTicket winningLottoTicket, int bonusNumber) {
        try {
            LottoNumber bonusBall = new LottoNumber(bonusNumber);
            winningLottoTicket.initializeBonusBall(bonusBall);
        } catch (IllegalArgumentException e) {
            OutputViewer.printErrorMessage(e.getMessage());
            getBonusBall(winningLottoTicket, InputViewer.inputBonusBallNumber());
        }
    }

    public static LottoResult calculateLottoResult(Tickets tickets,
                                                   WinningLottoTicket winningLottoTicket) {
        LottoResult lottoResult = new LottoResult();
        lottoResult.calculateWinningCount(tickets, winningLottoTicket);
        return lottoResult;
    }

    public static void calculateProfit(Money money, LottoResult lottoResult) {
        lottoResult.calculateProfitPercent(money);
    }

    public static ManualLottoTicketCount getManualLottoTicketCount(int allLottoTicketCount, int inputManualLottoCount) {
        try {
            ManualLottoTicketCount manualLottoTicketCount = new ManualLottoTicketCount(allLottoTicketCount,
                    inputManualLottoCount);
            return manualLottoTicketCount;
        } catch (IllegalArgumentException e) {
            OutputViewer.printErrorMessage(e.getMessage());
            return getManualLottoTicketCount(allLottoTicketCount, InputViewer.inputManualLottoCount());
        }
    }

    public static ManualLottoTickets getManualLottoTickets(ManualLottoTicketCount manualLottoTicketCount) {
        try {
            List<String> manualLottoNumbers = InputViewer.inputManualLottoTicketNumber(manualLottoTicketCount);
            List<LottoTicket> manualLottoTickets = ManualLottoTicketsGenerator.generateManualLottoTickets(
                    manualLottoNumbers);
            return new ManualLottoTickets(manualLottoTickets);
        } catch (IllegalArgumentException e) {
            OutputViewer.printErrorMessage(e.getMessage());
            return getManualLottoTickets(manualLottoTicketCount);
        }
    }

    public static List<LottoTicket> concatManualTicketsWithAutoTickets(ManualLottoTickets manualLottoTickets,
                                                                       AutoLottoTickets autoLottoTickets) {
        List<LottoTicket> manualLottoTicketList = manualLottoTickets.getTickets();
        List<LottoTicket> autoLottoTicketList = autoLottoTickets.getTickets();
        List<LottoTicket> lottoTickets = Stream.concat(manualLottoTicketList.stream(), autoLottoTicketList.stream())
                .collect(Collectors.toList());
        return lottoTickets;
    }
}
