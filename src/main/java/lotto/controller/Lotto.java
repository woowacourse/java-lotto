package lotto.controller;

import lotto.model.*;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Lotto {

    public static void lottoGame() {
        List<AutoNumbers> autoNumbers = new ArrayList<>();
        HashMap<String, Integer> resultCount = new HashMap<>();
        initialize(autoNumbers, resultCount);
        for (AutoNumbers autoNum : autoNumbers) {
            int count = isInWinNumber(autoNum);
            checkCountOverThree(resultCount, autoNum, count);
        }
        printCorrectResults(resultCount);

        double cnt = 0;
        cnt += LottoResult.THREE.result(resultCount.get("3"));
        cnt += LottoResult.FOUR.result(resultCount.get("4"));
        cnt += LottoResult.FIVE.result(resultCount.get("5"));
        cnt += LottoResult.FIVE_BONUS.result(resultCount.get("5+"));
        cnt += LottoResult.SIX.result(resultCount.get("6"));

        int yieldMoney = YieldMoney.countYieldMoney(cnt);
        OutputView.printYield(yieldMoney);
    }

    private static void checkCountOverThree(HashMap<String, Integer> resultCount, AutoNumbers autoNum, int count) {
        if (count >= 3) {
            String key = Integer.toString(count);
            checkCount(resultCount, autoNum, count, key);
        }
    }

    private static void printCorrectResults(HashMap<String, Integer> resultCount) {
        OutputView.printCorrectResult(resultCount.get("3"), LottoResult.THREE);
        OutputView.printCorrectResult(resultCount.get("4"), LottoResult.FOUR);
        OutputView.printCorrectResult(resultCount.get("5"), LottoResult.FIVE);
        OutputView.printBonusCorrectResult(resultCount.get("5+"));
        OutputView.printCorrectResult(resultCount.get("6"), LottoResult.SIX);
    }

    private static void initialize(List<AutoNumbers> autoNumbers, HashMap<String, Integer> resultCount) {
        LottoNumbers.lottoNumbersCreate();
        Initializer.initializePayment();
        int tryCount = Payment.payment / 1000;
        OutputView.printLottoCount(tryCount);
        Initializer.initializeResultCount(resultCount);
        Initializer.initializeAutoNumbers(autoNumbers, tryCount);
        OutputView.printAutoNumbers(autoNumbers);
        Initializer.initializeWinNumber();
        Initializer.initializeBonusNumber();
    }

    private static void checkCount(HashMap<String, Integer> resultCount, AutoNumbers autoNum, int count, String key) {
        if (count == 5) {
            isFivePlusBonus(resultCount, autoNum, key);
            return;
        }
        resultCount.put(key, resultCount.get(key) + 1);
    }

    private static void isFivePlusBonus(HashMap<String, Integer> resultCount, AutoNumbers autoNum, String key) {
        if (hasBonusBall(autoNum)) {
            resultCount.put("5+", resultCount.get("5+") + 1);
            return;
        }
        resultCount.put(key, resultCount.get(key) + 1);
    }

    public static int isInWinNumber(AutoNumbers autonumbers) {
        int count = 0;
        for (int autoNumber : autonumbers.getAutoNumbers()) {
            count += checkInWinNumber(autoNumber);
        }
        return count;
    }

    private static int checkInWinNumber(int autoNumber) {
        int count = 0;
        if (WinNumber.winNumbers.contains(autoNumber)) {
            count = 1;
        }
        return count;
    }

    public static boolean hasBonusBall(AutoNumbers autonumbers) {
        return autonumbers.getAutoNumbers().contains(BonusBall.bonusNo);
    }
}
