package controller;

import view.InputViewer;
import view.OutputViewer;
import domain.*;

public class LottoController {
    private static Money money;
    private static LottoTickets lottoTickets;
    private static WinningLottoTicket winningLottoTicket;
    private static WinningCalculator winningCalculator;

    public static void run() {
        initializeMoney();
        initializeLottoTickets();
        initializeWinningLottoTicket();
        initializeBonusBall();
        calculateWinningLottoTicket();
        OutputViewer.printResult(money, winningCalculator);
    }

    private static void calculateWinningLottoTicket() {
        winningCalculator = new WinningCalculator();
        winningCalculator.calculateWinningCount(lottoTickets, winningLottoTicket);
    }

    private static void initializeMoney() {
        try {
            String inputMoney = InputViewer.inputMoney();
            money = new Money(inputMoney);
            OutputViewer.printLottoTicketsCount(money);
        } catch (IllegalArgumentException e) {
            OutputViewer.printErrorMessage(e.getMessage());
            initializeMoney();
        }
    }

    private static void initializeLottoTickets() {
        try {
            lottoTickets = new LottoTickets(LottoTicketsGenerator.generateLottoTickets(money.getLottoTicketCount()));
            OutputViewer.printLottoTickets(lottoTickets);
        } catch (IllegalArgumentException e) {
            OutputViewer.printErrorMessage(e.getMessage());
            initializeLottoTickets();
        }
    }

    private static void initializeWinningLottoTicket() {
        try {
            String winningTicketNumber = InputViewer.inputWinningLottoTicketNumber();
            winningLottoTicket = new WinningLottoTicket(winningTicketNumber);
        } catch (IllegalArgumentException e) {
            OutputViewer.printErrorMessage(e.getMessage());
            initializeWinningLottoTicket();
        }
    }

    private static void initializeBonusBall() {
        try {
            String bonusBallNumber = InputViewer.inputBonusBallNumber();
            winningLottoTicket.initializeBonusBall(bonusBallNumber);
        } catch (IllegalArgumentException e) {
            OutputViewer.printErrorMessage(e.getMessage());
            initializeBonusBall();
        }
    }
}
