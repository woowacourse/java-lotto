package lotto.controller;

import lotto.model.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.HashMap;
import java.util.List;

public class Initializer {

    public static final String THREE = "3";
    public static final String FOUR = "4";
    public static final String FIVE = "5";
    public static final String FIVE_BONUS = "5+";
    public static final String SIX = "6";
    public static final int PAYMENT_UNIT = 1000;
    public static final int INITIAL_NUMBER = 0;

    public static void initialize(List<AutoNumber> autoNumbers, HashMap<String, Integer> resultCount) {
        LottoNumbers.lottoNumbersCreate();
        initializePayment();
        int tryCount = Payment.payment / PAYMENT_UNIT;
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
        resultCount.put(THREE, INITIAL_NUMBER);
        resultCount.put(FOUR, INITIAL_NUMBER);
        resultCount.put(FIVE, INITIAL_NUMBER);
        resultCount.put(FIVE_BONUS, INITIAL_NUMBER);
        resultCount.put(SIX, INITIAL_NUMBER);
    }
}
