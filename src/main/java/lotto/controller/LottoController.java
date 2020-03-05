package lotto.controller;

import jdk.internal.util.xml.impl.Input;
import lotto.model.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

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

    private void makeLottoTickets() {
        OutputView.printInputManualCount();
        int manualTicketCount = InputView.inputManualCount();
        OutputView.printTicketCount(manualTicketCount, payment);
        OutputView.printInputManualTicket();
        List<LottoTicket> manualTickets = new ArrayList<>();
        for (int count = 0; count < manualTicketCount; count++) {
            manualTickets.add(InputView.inputLottoTicket());
        }
        TicketInformation ticketInformation = new TicketInformation(payment, manualTicketCount, manualTickets);
        lottoTickets.combineTickets(ticketInformation);
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