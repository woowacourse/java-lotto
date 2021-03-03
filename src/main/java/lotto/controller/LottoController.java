package lotto.controller;

import lotto.domain.*;
import lotto.view.LottoView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class LottoController {
    private static Lottos lottos;

    public void startLotto() {
        Money money = new Money(LottoView.requestMoney());
        makeAllLotto(money);
        LottoView.displayLottoCount(lottos.getManualCount(), lottos.getRandomCount());
        LottoView.displayLottoGroup(lottos);
    }

    public void endLotto() {
        WinningLotto winningLotto = makeWinningLotto();
        LottoView.displayResultMessage();
        Map<Rank, Integer> countByRank = countEachRank(winningLotto);
        countByRank.forEach(LottoView::displayResult);
        LottoView.displayEarningRate(Lottos.findResult(countByRank));
    }

    private void makeAllLotto(Money money) {
        String manualCount = LottoView.requestManualLottoCount();
        lottos = new Lottos(money, manualCount);
        LottoView.displayManualNumberMessage();
        List<Lotto> manualLottos = new ArrayList<>();
        for (int i = 0; i < Integer.parseInt(manualCount); i++) {
            manualLottos.add(makeManualLotto());
        }
        lottos.generateManualLotto(manualLottos);
    }

    public Lotto makeManualLotto() {
        String manualNumberInput = LottoView.requestManualNumber();
        return Lotto.changeStringToLotto(manualNumberInput);
    }

    private WinningLotto makeWinningLotto() {
        String winningInput = LottoView.requestWinningNumber();
        Lotto winLotto = Lotto.changeStringToLotto(winningInput);
        String bonusInput = LottoView.requestBonusBallNumber();
        return new WinningLotto(winLotto, bonusInput);
    }

    private Map<Rank, Integer> countEachRank(WinningLotto winningLotto) {
        ArrayList<Rank> wins = lottos.drawLotto(winningLotto);
        Map<Rank, Integer> countByRank = new TreeMap<>();
        for (int i = 1; i < Rank.values().length; i++) {
            Rank rank = Rank.values()[i];
            int rankCount = (int) wins.stream().filter(win -> win == rank).count();
            countByRank.put(rank, rankCount);
        }
        return countByRank;
    }
}