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
        OutputView.printLottoCount(payment.getPayment());
        lottoResult = new LottoResult();
        lottoTickets = new LottoTickets(payment.countLottoTickets());
        OutputView.printAutoNumbers(lottoTickets);
        OutputView.printInputWinNumber();
        winNumber = new WinNumber(InputView.inputWinNumber());
        OutputView.printInputBonusNumber();
        bonusBall = new BonusBall(winNumber, InputView.inputBonusBall());
    }

    public void lottoGame() {
        for (LottoTicket lottoTicket : lottoTickets.getLottoTickets()) {
            lottoResult.checkCount(lottoTicket, winNumber, bonusBall);
        }
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