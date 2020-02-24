package lotto.controller;

import lotto.model.*;
import lotto.view.OutputView;

public class Lotto {
    private Payment payment;
    private LottoResult lottoResult;
    private LottoTickets lottoTickets;
    private WinNumber winNumber;
    private BonusBall bonusBall;

    public Lotto() {
        payment = LottoMembersInitializer.initializePayment();
        OutputView.printLottoCount(payment.getPayment());
        lottoResult = LottoMembersInitializer.initializeResultCount();
        lottoTickets = new LottoTickets(payment.countLottoTickets());
        OutputView.printAutoNumbers(lottoTickets);
        winNumber = LottoMembersInitializer.initializeWinNumber();
        bonusBall = LottoMembersInitializer.initializeBonusNumber(winNumber);
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
        OutputView.printCorrectResult(lottoResult.rankResult(LottoRank.FIFTH.name()), LottoRank.FIFTH.getRank(), LottoRank.FIFTH.getPrize());
        OutputView.printCorrectResult(lottoResult.rankResult(LottoRank.FOURTH.name()), LottoRank.FOURTH.getRank(), LottoRank.FOURTH.getPrize());
        OutputView.printCorrectResult(lottoResult.rankResult(LottoRank.THIRD.name()), LottoRank.THIRD.getRank(), LottoRank.THIRD.getPrize());
        OutputView.printBonusCorrectResult(lottoResult.rankResult(LottoRank.SECOND.name()));
        OutputView.printCorrectResult(lottoResult.rankResult(LottoRank.FIRST.name()), LottoRank.FIRST.getRank(), LottoRank.FIRST.getPrize());
    }
}