package lotto.controller;

import lotto.model.*;
import lotto.utils.LottoRules;
import lotto.view.OutputView;

public class Lotto {
    private Payment payment;
    private LottoResult lottoResult;
    private AutoNumbers autoNumbers;
    private WinNumber winNumber;
    private BonusBall bonusBall;

    public Lotto() {
        payment = LottoMembersInitializer.initializePayment();
        OutputView.printLottoCount(payment.getPayment());
        lottoResult = LottoMembersInitializer.initializeResultCount();
        autoNumbers = new AutoNumbers(payment.getPaymentCount());
        OutputView.printAutoNumbers(autoNumbers);
        winNumber = LottoMembersInitializer.initializeWinNumber();
        bonusBall = LottoMembersInitializer.initializeBonusNumber(winNumber);
    }

    public void lottoGame() {
        for (AutoNumber autoNum : autoNumbers.getAutoNumbers()) {
            int count = isInWinNumber(autoNum);
            checkCountOverThree(autoNum, count);
        }
        OutputView.printResult();
        printCorrectResults();
        OutputView.printYield(YieldMoney.countYieldMoney(payment, Prize.sumPrize(lottoResult)));
    }

    private int isInWinNumber(AutoNumber autoNumber) {
        return (int) autoNumber.getAutoNumber()
                .stream()
                .filter(x -> winNumber.contains(x))
                .count();
    }

    private void checkCountOverThree(AutoNumber autoNum, int count) {
        if (LottoRank.checkNoPrize(count)) {
            checkCount(autoNum, count);
        }
    }

    private void checkCount(AutoNumber autoNumber, int count) {
        if (LottoRank.checkThirdWinner(count)) {
            isSecondWin(autoNumber, count);
            return;
        }
        lottoResult.putValue(count, lottoResult.getKey(count) + LottoRules.WINNING_COUNT.getNumber());
    }

    private void isSecondWin(AutoNumber autoNumber, int count) {
        if (autoNumber.contains(bonusBall.getBonusNumber())) {
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