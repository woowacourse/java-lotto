package lotto.controller;

import lotto.model.*;
import lotto.utils.LottoRules;
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
        for (LottoTicket autoNum : lottoTickets.getLottoTickets()) {
            int count = isInWinNumber(autoNum);
            checkCountOverThree(autoNum, count);
        }
        OutputView.printResult();
        printCorrectResults();
        OutputView.printYield(YieldMoney.countYieldMoney(payment, Prize.sumPrize(lottoResult)));
    }

    private int isInWinNumber(LottoTicket lottoTicket) {
        return (int) lottoTicket.getAutoNumber()
                .stream()
                .filter(x -> winNumber.contains(x))
                .count();
    }

    private void checkCountOverThree(LottoTicket autoNum, int count) {
        if (LottoRank.checkNoPrize(count)) {
            checkCount(autoNum, count);
        }
    }

    private void checkCount(LottoTicket lottoTicket, int count) {
        if (LottoRank.checkThirdWinner(count)) {
            isSecondWin(lottoTicket, count);
            return;
        }
        lottoResult.putValue(count, lottoResult.getKey(count) + LottoRules.WINNING_COUNT.getNumber());
    }

    private void isSecondWin(LottoTicket lottoTicket, int count) {
        if (lottoTicket.contains(bonusBall.getBonusNumber())) {
            lottoResult.putValue(LottoRank.SECOND.getRank(), lottoResult.getKey(LottoRank.SECOND.getRank()) + LottoRules.WINNING_COUNT.getNumber());
            return;
        }
        lottoResult.putValue(count, lottoResult.getKey(count) + LottoRules.WINNING_COUNT.getNumber());
    }

    public void printCorrectResults() {
        OutputView.printCorrectResult(lottoResult.getKey(LottoRank.FIFTH.getRank()), LottoRank.FIFTH.getRank(), LottoRank.FIFTH.getPrize());
        OutputView.printCorrectResult(lottoResult.getKey(LottoRank.FOURTH.getRank()), LottoRank.FOURTH.getRank(), LottoRank.FOURTH.getPrize());
        OutputView.printCorrectResult(lottoResult.getKey(LottoRank.THIRD.getRank()), LottoRank.THIRD.getRank(), LottoRank.THIRD.getPrize());
        OutputView.printBonusCorrectResult(lottoResult.getKey(LottoRank.SECOND.getRank()));
        OutputView.printCorrectResult(lottoResult.getKey(LottoRank.FIRST.getRank()), LottoRank.FIRST.getRank(), LottoRank.FIRST.getPrize());
    }
}