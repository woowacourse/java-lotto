package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;
import lotto.view.LottoView;

import java.util.*;
import java.util.stream.Collectors;

public class LottoController {
    private static final ArrayList<Rank> wins = new ArrayList<>();
    private static final Map<Rank, Integer> countByRank = new TreeMap<>();
    private static final String DELIMITER = ",";
    private static Lottos lottos;
    private static WinningLotto winningLotto;

    public void startLotto() {
        makeAllLotto();
        LottoView.displayLottoCount(lottos.getManualCount(), lottos.getRandomCount());
        LottoView.displayLottoGroup(lottos);
        makeWinningLotto();
    }

    public void endLotto() {
        drawLotto();
        drawResult();
        LottoView.displayEarningRate(Lottos.findResult(countByRank));
    }

    private void makeAllLotto() {
        String totalMoney = LottoView.requestMoney();
        String manualCount = LottoView.requestManualLottoCount();
        lottos = new Lottos(totalMoney, manualCount);
        LottoView.displayManualNumberMessage();
        for (int i = 0; i < Integer.parseInt(manualCount); i++) {
            lottos.addManualLotto(makeManualLotto());
        }
    }

    private Lotto makeManualLotto() {
        String winningInput = LottoView.requestManualNumber();
        return new Lotto(generateManualLotto(winningInput));
    }

    private void makeWinningLotto() {
        String winningInput = LottoView.requestWinningNumber();
        Lotto winLotto = new Lotto(generateManualLotto(winningInput));
        String bonusInput = LottoView.requestBonusBallNumber();
        winningLotto = new WinningLotto(winLotto, bonusInput);
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

    private ArrayList<Integer> generateManualLotto(String numberInput) {
        return new ArrayList<>(changeToList(numberInput));
    }

    private List<Integer> changeToList(String numberInput) {
        return Arrays.stream(numberInput.split(DELIMITER))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private void countEachRank() {
        for (int i = 1; i < Rank.values().length; i++) {
            Rank rank = Rank.values()[i];
            int rankCount = (int) wins.stream().filter(win -> win == rank).count();
            countByRank.put(rank, rankCount);
        }
    }
}
