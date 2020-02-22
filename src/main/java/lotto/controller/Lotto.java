package lotto.controller;

import lotto.model.*;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lotto {
    public static final int WINNING_COUNT = 1;
    private Payment payment;
    private LottoResult lottoResult;
    private AutoNumbers autoNumbers;
    private WinNumber winNumber;
    private BonusBall bonusBall;

    public Lotto() {
        payment = DomainsInitializer.initializePayment();
        OutputView.printLottoCount(payment.getPayment());
        lottoResult = DomainsInitializer.initializeResultCount();
        autoNumbers = new AutoNumbers(payment.getPaymentCount());
        OutputView.printAutoNumbers(autoNumbers);
        winNumber = DomainsInitializer.initializeWinNumber();
        bonusBall = DomainsInitializer.initializeBonusNumber(winNumber);
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
        String correctCount = Integer.toString(count);
        if (LottoRank.isCorrectNumberFive(count)) {
            isSecondWin(autoNumber, correctCount);
            return;
        }
        lottoResult.putValue(correctCount, lottoResult.getKey(correctCount) + WINNING_COUNT);
    }

    private void isSecondWin(AutoNumber autoNumber, String correctCount) {
        if (autoNumber.contains(bonusBall.getBonusNumber())) {
            lottoResult.putValue(LottoRank.SECOND.getRank(), lottoResult.getKey(LottoRank.SECOND.getRank()) + WINNING_COUNT);
            return;
        }
        lottoResult.putValue(correctCount, lottoResult.getKey(correctCount) + WINNING_COUNT);
    }

    public void printCorrectResults() {
        OutputView.printCorrectResult(lottoResult.getKey(LottoRank.FIFTH.getRank()), LottoRank.FIFTH.getCorrect(), LottoRank.FIFTH.getPrize());
        OutputView.printCorrectResult(lottoResult.getKey(LottoRank.FOURTH.getRank()), LottoRank.FOURTH.getCorrect(), LottoRank.FOURTH.getPrize());
        OutputView.printCorrectResult(lottoResult.getKey(LottoRank.THIRD.getRank()), LottoRank.THIRD.getCorrect(), LottoRank.THIRD.getPrize());
        OutputView.printBonusCorrectResult(lottoResult.getKey(LottoRank.SECOND.getRank()));
        OutputView.printCorrectResult(lottoResult.getKey(LottoRank.FIRST.getRank()), LottoRank.FIRST.getCorrect(), LottoRank.FIRST.getPrize());
    }
}