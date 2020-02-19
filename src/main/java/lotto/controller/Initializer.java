package lotto.controller;

import lotto.model.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Initializer {
    public static void initializePayment() {
        OutputView.printinput();
        new Payment(InputView.input());
    }

    public static void initializeBonusNumber() {
        OutputView.printInputBonusNumber();
        new BonusBall(InputView.input());
    }

    public static void initializeWinNumber() {
        OutputView.printInputWinNumber();
        new WinNumber(InputView.input());
    }

    public static void initializeAutoNumbers(List<AutoNumbers> autoNumbers, int tryCount) {
        for (int i = 0; i < tryCount; i++) {
            autoNumbers.add(new AutoNumbers());
        }
    }

    public static void initializeResultCount(HashMap<String, Integer> resultCount) {
        resultCount.put("3", 0);
        resultCount.put("4", 0);
        resultCount.put("5", 0);
        resultCount.put("5+", 0);
        resultCount.put("6", 0);
    }
}
