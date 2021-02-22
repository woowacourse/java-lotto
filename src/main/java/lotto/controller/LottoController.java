package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;
import lotto.view.LottoView;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class LottoController {
    private static final ArrayList<Rank> wins = new ArrayList<>();
    private static final Map<Rank, Integer> countByRank = new TreeMap<>();
    private static Lottos lottos;
    private static WinningLotto winningLotto;

    public void startLotto() {
        lottos = new Lottos(LottoView.requestMoney());
        LottoView.displayLottoCount(lottos.getLottoGroup().size());
        LottoView.displayLottoGroup(lottos);
        makeWinningLotto();
    }

    public void endLotto() {
        drawLotto();
        drawResult();
        LottoView.displayEarningRate(Lottos.findResult(countByRank));
    }

    private void makeWinningLotto() {
        String winningInput = LottoView.requestWinningNumber();
        String bonusInput = LottoView.requestBonusBallNumber();
        winningLotto = new WinningLotto(winningInput, bonusInput);
    }

    private void drawLotto() {
        ArrayList<Lotto> lottoGroup = lottos.getLottoGroup();
        for (Lotto lotto : lottoGroup) {
            Rank rank = winningLotto.findRank(lotto);
            wins.add(rank);
        }
    }

    private void drawResult() {
        LottoView.displayResultMessage();
        countEachRank();
        countByRank.forEach(LottoView::displayResult);
    }

    private void countEachRank() {
        for (int i = 1; i < Rank.values().length; i++) {
            Rank rank = Rank.values()[i];
            int rankCount = (int) wins.stream().filter(win -> win == rank).count();
            countByRank.put(rank, rankCount);
        }
    }
}
