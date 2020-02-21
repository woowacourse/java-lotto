package lotto.controller;

import lotto.model.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;
import java.util.Map;

public class DomainsInitializer {
    public static final int INITIAL_NUMBER = 0;

    public static void initialize(List<AutoNumber> autoNumbers, Map<String, Integer> resultCount) {
        Payment payment = initializePayment();
        OutputView.printLottoCount(payment.getPayment());
        initializeResultCount(resultCount);
        initializeAutoNumbers(autoNumbers, payment.getPayment());
        OutputView.printAutoNumbers(autoNumbers);
        initializeWinNumber();
        initializeBonusNumber();
    }

    private static Payment initializePayment() {
        OutputView.printinput();
        return new Payment(InputView.input());
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

    private static void initializeResultCount(Map<String, Integer> resultCount) {
        resultCount.put(LottoRank.FIFTH.getRank(), INITIAL_NUMBER);
        resultCount.put(LottoRank.FOURTH.getRank(), INITIAL_NUMBER);
        resultCount.put(LottoRank.THIRD.getRank(), INITIAL_NUMBER);
        resultCount.put(LottoRank.SECOND.getRank(), INITIAL_NUMBER);
        resultCount.put(LottoRank.FIRST.getRank(), INITIAL_NUMBER);
    }
}
