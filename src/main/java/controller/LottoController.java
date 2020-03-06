package controller;

import domain.*;
import view.InputViewer;
import view.OutputViewer;

import java.util.List;

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

    public static LottoTickets getLottoTickets(int manualLottoTicketCounts, int autoLottoTicketCount) {
        try {
            List<String> manualLottoNumbers = InputViewer.inputManualLottoTicketNumber(manualLottoTicketCounts);
            LottoTickets lottoTickets = TicketsFactory.getTickets(manualLottoNumbers, autoLottoTicketCount);
            return lottoTickets;
        } catch (IllegalArgumentException e) {
            OutputViewer.printErrorMessage(e.getMessage());
            return getLottoTickets(manualLottoTicketCounts, autoLottoTicketCount);
        }
    }

    public static WinningLottoTicket getWinningLottoTicket() {
        try {
            LottoTicket winningLottoTicket = getWinningLottoTicketNumbers();
            LottoNumber bonusBall = getBonusBall();
            return new WinningLottoTicket(winningLottoTicket, bonusBall);
        } catch (IllegalArgumentException e) {
            OutputViewer.printErrorMessage(e.getMessage());
            return getWinningLottoTicket();
        }
    }

    private static LottoTicket getWinningLottoTicketNumbers() {
        try {
            String lottoNumber = InputViewer.inputWinningLottoTicketNumber();
            List<LottoNumber> lottoNumbers = LottoNumberSplit.initializeLottoNumbers(lottoNumber);
            LottoTicket winningLottoTicket = new LottoTicket(lottoNumbers);
            return winningLottoTicket;
        } catch (IllegalArgumentException e) {
            OutputViewer.printErrorMessage(e.getMessage());
            return getWinningLottoTicketNumbers();
        }
    }

    private static LottoNumber getBonusBall() {
        try {
            int bonusNumber = InputViewer.inputBonusBallNumber();
            LottoNumber bonusBall = LottoNumbers.getLottoNumber(bonusNumber);
            return bonusBall;
        } catch (IllegalArgumentException e) {
            OutputViewer.printErrorMessage(e.getMessage());
            return getBonusBall();
        }
    }
}
