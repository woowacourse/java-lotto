package lotto.controller;

import lotto.model.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.HashMap;
import java.util.List;

public class Initializer {
    public static void initialize(List<AutoNumber> autoNumbers, HashMap<String, Integer> resultCount) {
        LottoNumbers.lottoNumbersCreate();
        initializePayment();
        int tryCount = Payment.payment / 1000;
        OutputView.printLottoCount(tryCount);
        initializeResultCount(resultCount);
        initializeAutoNumbers(autoNumbers, tryCount);
        OutputView.printAutoNumbers(autoNumbers);
        initializeWinNumber();
        initializeBonusNumber();
    }

    private static void initializePayment() {
        OutputView.printinput();
        new Payment(InputView.input());
    }

    private static void initializeBonusNumber() {
        OutputView.printInputBonusNumber();
        new BonusBall(InputView.input());
    }

    private static void initializeWinNumber() {
        OutputView.printInputWinNumber();
        new WinNumber(InputView.input());
    }

    private static void initializeAutoNumbers(List<AutoNumber> autoNumbers, int tryCount) {
        for (int i = 0; i < tryCount; i++) {
            autoNumbers.add(new AutoNumber());
        }
    }

    private static void initializeResultCount(HashMap<String, Integer> resultCount) {
        resultCount.put("3", 0);
        resultCount.put("4", 0);
        resultCount.put("5", 0);
        resultCount.put("5+", 0);
        resultCount.put("6", 0);
    }
}
