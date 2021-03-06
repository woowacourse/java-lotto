package lotto.controller;

import lotto.domain.*;
import lotto.view.LottoView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class LottoController {
    private static Lottos lottos;

    public void run() {
        try {
            Money money = new Money(LottoView.requestMoney());
            startLotto(money);
            endLotto(money);
        } catch (IllegalArgumentException e) {
            LottoView.displayErrorMessage(e.getMessage());
            run();
        }
    }

    private void startLotto(Money money) {
        int manualCount = makeAllLotto(money);
        LottoView.displayLottoCount(manualCount, money.getLeftCount(manualCount));
        LottoView.displayLottoGroup(lottos);
    }

    private void endLotto(Money money) {
        WinningLotto winningLotto = makeWinningLotto();
        LottoView.displayResultMessage();
        Result result = lottos.drawLotto(winningLotto);
        result.getCountByRank().forEach(LottoView::displayResult);
        LottoView.displayEarningRate(result.findEarningRate(money));
    }

    private int makeAllLotto(Money money) {
        String manualCount = LottoView.requestManualLottoCount();
        lottos = new Lottos(money, manualCount);
        lottos.generateManualLotto(makeManualLottos(manualCount));
        return Integer.parseInt(manualCount);
    }

    private List<String> makeManualLottos(String manualCount) {
        LottoView.displayManualNumberMessage();
        List<String> manualLottoNumbers = new ArrayList<>();
        for (int i = 0; i < Integer.parseInt(manualCount); i++) {
            manualLottoNumbers.add(LottoView.requestManualNumber());
        }
        return manualLottoNumbers;
    }

    private WinningLotto makeWinningLotto() {
        String winningInput = LottoView.requestWinningNumber();
        Lotto winLotto = Lotto.from(winningInput);
        String bonusInput = LottoView.requestBonusBallNumber();
        return new WinningLotto(winLotto, bonusInput);
    }
}