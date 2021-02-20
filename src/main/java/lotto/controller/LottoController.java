package lotto.controller;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoGroup;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;
import lotto.view.LottoView;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class LottoController {

    private static final List<Rank> wins = new ArrayList<>();
    private static final Map<Rank, Integer> countByRank = new TreeMap<>();
    private static LottoGroup lottoGroup;
    private static WinningLotto winningLotto;

    private static void countEachRank() {
        for (int i = 1; i < Rank.values().length; i++) {
            Rank rank = Rank.values()[i];
            int rankCount = (int) wins.stream().filter(win -> win == rank).count();
            countByRank.put(rank, rankCount);
        }
    }

    private static void drawResult() {
        LottoView.displayResultMessage();
        countEachRank();
        countByRank.forEach((rank, rankCount) ->
            LottoView.displayResult(rank, rankCount));
    }

    public void startLotto() {
        lottoGroup = new LottoGroup(LottoView.requestMoney());
        LottoView.buyLotto(lottoGroup.getCount());
        LottoView.printLottoGroup(lottoGroup);
        makeWinningLotto();
    }

    public void endLotto() {
        drawLotto();
        drawResult();
        LottoView.displayEarningRate(LottoGroup.findResult(countByRank));
    }

    private void makeWinningLotto() {
        String winningInput = LottoView.requestWinningNumber();
        String bonusInput = LottoView.requestBonusBallNumber();
        winningLotto = new WinningLotto(winningInput, bonusInput);
    }

    private void drawLotto() {
        ArrayList<Lotto> lottoGroup = LottoController.lottoGroup.getLottoGroup();
        for (Lotto lotto : lottoGroup) {
            Rank rank = winningLotto.findRank(lotto);
            wins.add(rank);
        }
    }
}
