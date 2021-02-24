package lotto.controller;

import lotto.domain.*;
import lotto.view.LottoView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class LottoController {
    private static final ArrayList<Rank> wins = new ArrayList<>();
    private static final Map<Rank, Integer> countByRank = new TreeMap<>();
    private static final String DELIMITER = ",";
    private static Lottos lottos;
    private static WinningLotto winningLotto;

    public void startLotto() {
        Money money = new Money(LottoView.requestMoney());
        makeAllLotto(money);
        LottoView.displayLottoCount(lottos.getManualCount(), lottos.getRandomCount());
        LottoView.displayLottoGroup(lottos);
        makeWinningLotto();
    }

    public void endLotto() {
        drawLotto();
        drawResult();
        LottoView.displayEarningRate(Lottos.findResult(countByRank));
    }

    private void makeAllLotto(Money money) {
        String manualCount = LottoView.requestManualLottoCount();
        lottos = new Lottos(money, manualCount);
        LottoView.displayManualNumberMessage();
        for (int i = 0; i < Integer.parseInt(manualCount); i++) {
            lottos.addManualLotto(makeManualLotto());
        }
    }

    private Lotto makeManualLotto() {
        String winningInput = LottoView.requestManualNumber();
        return new Lotto(changeToList(winningInput));
    }

    private void makeWinningLotto() {
        String winningInput = LottoView.requestWinningNumber();
        Lotto winLotto = new Lotto(changeToList(winningInput));
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

    private ArrayList<String> changeToList(String numberInput) {
        return Arrays.stream(numberInput.split(DELIMITER))
                .map(String::trim)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private void countEachRank() {
        for (int i = 1; i < Rank.values().length; i++) {
            Rank rank = Rank.values()[i];
            int rankCount = (int) wins.stream().filter(win -> win == rank).count();
            countByRank.put(rank, rankCount);
        }
    }
}
