package lotto.controller;

import lotto.model.*;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private Payment payment;
    private LottoResult lottoResult;
    private LottoTickets lottoTickets;
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
        TicketNumber ticketNumber = new TicketNumber(payment.countTickets(), InputView.inputManualCount());
        OutputView.printTicketCount(ticketNumber.getManualTicket(), ticketNumber.getAutoTicket());
        OutputView.printInputManualTicket();
        lottoTickets.combineTickets(ticketNumber);
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