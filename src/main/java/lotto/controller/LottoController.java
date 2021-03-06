package lotto.controller;

import lotto.domain.*;
import lotto.view.LottoView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {
    private static Lottos lottos;

    public void run() {
        try {
            final Money money = new Money(LottoView.requestMoney());
            startLotto(money);
            endLotto(money);
        } catch (IllegalArgumentException e) {
            LottoView.displayErrorMessage(e.getMessage());
            run();
        }
    }

    private void startLotto(final Money money) {
        final int manualCount = makeAllLotto(money);
        LottoView.displayLottoCount(manualCount, money.getLeftCount(manualCount));
        LottoView.displayLottoGroup(lottos);
    }

    private void endLotto(final Money money) {
        final WinningLotto winningLotto = makeWinningLotto();
        LottoView.displayResultMessage();
        final Result result = lottos.drawLotto(winningLotto);
        result.getCountByRank().forEach(LottoView::displayResult);
        LottoView.displayEarningRate(result.findEarningRate(money));
    }

    private int makeAllLotto(final Money money) {
        final String manualCount = LottoView.requestManualLottoCount();
        lottos = new Lottos(money, manualCount);
        lottos.generateManualLotto(makeManualLottos(manualCount));
        return Integer.parseInt(manualCount);
    }

    private List<String> makeManualLottos(final String manualCount) {
        LottoView.displayManualNumberMessage();
        final List<String> manualLottoNumbers = new ArrayList<>();
        for (int i = 0; i < Integer.parseInt(manualCount); i++) {
            manualLottoNumbers.add(LottoView.requestManualNumber());
        }
        return manualLottoNumbers;
    }

    private WinningLotto makeWinningLotto() {
        final String winningInput = LottoView.requestWinningNumber();
        final Lotto winLotto = Lotto.from(winningInput);
        final String bonusInput = LottoView.requestBonusBallNumber();
        return new WinningLotto(winLotto, bonusInput);
    }
}