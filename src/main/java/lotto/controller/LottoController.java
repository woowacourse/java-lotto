package lotto.controller;

import lotto.model.*;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private Payment payment;
    private LottoResult lottoResult;
    private LottoTickets lottoTickets;
    private TicketNumber ticketNumber;
    private WinNumber winNumber;
    private BonusBall bonusBall;

    public LottoController() {
        OutputView.printinput();
        payment = new Payment(InputView.inputPayment());
        lottoTickets = new LottoTickets();
        makeLottoTickets();
        OutputView.printAutoNumbers(lottoTickets);
        OutputView.printInputWinNumber();
        winNumber = new WinNumber(InputView.inputWinNumber());
        OutputView.printInputBonusNumber();
        bonusBall = new BonusBall(winNumber, InputView.inputBonusBall());
    }

    public void makeLottoTickets() {
        OutputView.printInputManualCount();
        ticketNumber = new TicketNumber(payment.countAutoTickets(), InputView.inputManualCount());
        OutputView.printInputManualTicket();
        for (int i = 0; i < ticketNumber.getManualTicket(); i++) {
            LottoTicket lottoTicket = new LottoTicket(InputView.inputLottoTicket());
            lottoTickets.manualTickets(lottoTicket);
        }
        OutputView.printTicketCount(ticketNumber.getManualTicket(), ticketNumber.getAutoTicket());
        lottoTickets.autoTickets(ticketNumber.getAutoTicket());
    }

    public void lottoGame() {
        lottoResult = lottoTickets.matchLottoResult(winNumber, bonusBall);
        OutputView.printResult();
        printCorrectResults();
        OutputView.printYield(YieldMoney.countYieldMoney(payment, Prize.sumPrize(lottoResult)));
    }

    public void printCorrectResults() {
        for (LottoRank lottoRank : LottoRank.values()) {
            OutputView.printCorrectResult(lottoResult.rankResult(lottoRank), lottoRank);
        }
    }
}