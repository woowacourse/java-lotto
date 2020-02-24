package lotto.controller;

import lotto.model.*;
import lotto.view.OutputView;

public class Lotto {
    public static final int WINNING_COUNT = 1;
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
        if (LottoRank.isCorrectNumberOverThree(count)) {
            checkCount(autoNum, count);
        }
    }

    private void checkCount(AutoNumber autoNumber, int count) {
        if (LottoRank.isCorrectNumberFive(count)) {
            isSecondWin(autoNumber, count);
            return;
        }
        lottoResult.putValue(count, lottoResult.getKey(count) + WINNING_COUNT);
    }

    private void isSecondWin(AutoNumber autoNumber, int count) {
        if (autoNumber.contains(bonusBall.getBonusNumber())) {
            lottoResult.putValue(LottoRank.SECOND.getCorrect(), lottoResult.getKey(LottoRank.SECOND.getCorrect()) + WINNING_COUNT);
            return;
        }
        lottoResult.putValue(count, lottoResult.getKey(count) + WINNING_COUNT);
    }

    public void printCorrectResults() {
        OutputView.printCorrectResult(lottoResult.getKey(LottoRank.FIFTH.getCorrect()), LottoRank.FIFTH.getCorrect(), LottoRank.FIFTH.getPrize());
        OutputView.printCorrectResult(lottoResult.getKey(LottoRank.FOURTH.getCorrect()), LottoRank.FOURTH.getCorrect(), LottoRank.FOURTH.getPrize());
        OutputView.printCorrectResult(lottoResult.getKey(LottoRank.THIRD.getCorrect()), LottoRank.THIRD.getCorrect(), LottoRank.THIRD.getPrize());
        OutputView.printBonusCorrectResult(lottoResult.getKey(LottoRank.SECOND.getCorrect()));
        OutputView.printCorrectResult(lottoResult.getKey(LottoRank.FIRST.getCorrect()), LottoRank.FIRST.getCorrect(), LottoRank.FIRST.getPrize());
    }
}