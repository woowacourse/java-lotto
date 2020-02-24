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
//        for (LottoRank lottoRank : LottoRank.values()) {
//            OutputView.printCorrectResult();
//        }
        OutputView.printCorrectResult(lottoResult.rankResult(LottoRank.FIFTH.name()), LottoRank.FIFTH.getRank(), LottoRank.FIFTH.getPrize());
        OutputView.printCorrectResult(lottoResult.rankResult(LottoRank.FOURTH.name()), LottoRank.FOURTH.getRank(), LottoRank.FOURTH.getPrize());
        OutputView.printCorrectResult(lottoResult.rankResult(LottoRank.THIRD.name()), LottoRank.THIRD.getRank(), LottoRank.THIRD.getPrize());
        OutputView.printBonusCorrectResult(lottoResult.rankResult(LottoRank.SECOND.name()));
        OutputView.printCorrectResult(lottoResult.rankResult(LottoRank.FIRST.name()), LottoRank.FIRST.getRank(), LottoRank.FIRST.getPrize());
    }
}