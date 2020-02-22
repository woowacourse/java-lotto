package lotto.controller;

import lotto.model.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.HashMap;
import java.util.Map;

public class DomainsInitializer {
    private static final int INITIAL_NUMBER = 0;

    public static Payment initializePayment() {
        OutputView.printinput();
        return new Payment(InputView.input());
    }

    public static BonusBall initializeBonusNumber(WinNumber winNumber) {
        OutputView.printInputBonusNumber();
        return new BonusBall(winNumber, InputView.input());
    }

    public static WinNumber initializeWinNumber() {
        OutputView.printInputWinNumber();
        return new WinNumber(InputView.input());
    }

    public static LottoResult initializeResultCount() {
        Map<String, Integer> lottoResult = new HashMap<>();
        lottoResult.put(LottoRank.FIFTH.getRank(), INITIAL_NUMBER);
        lottoResult.put(LottoRank.FOURTH.getRank(), INITIAL_NUMBER);
        lottoResult.put(LottoRank.THIRD.getRank(), INITIAL_NUMBER);
        lottoResult.put(LottoRank.SECOND.getRank(), INITIAL_NUMBER);
        lottoResult.put(LottoRank.FIRST.getRank(), INITIAL_NUMBER);
        return new LottoResult(lottoResult);
    }
}
