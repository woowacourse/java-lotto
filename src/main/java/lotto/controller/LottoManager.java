package lotto.controller;

import lotto.model.*;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoManager {

    public static void lotto(AutoTickets autoTickets, WinNumbers winNumbers, BonusBallNo bonusBallNo) {
        autoTickets.matchNumberResult(winNumbers, bonusBallNo);
    }
}