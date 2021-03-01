package lotto.controller;

import lotto.domain.*;
import lotto.view.LottoView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class LottoController {
    private static final String DELIMITER = ",";
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
        for (int i = 0; i < Integer.parseInt(manualCount); i++) {
            lottos.addManualLotto(makeManualLotto());
        }
    }

    private Lotto makeManualLotto() {
        String winningInput = LottoView.requestManualNumber();
        return new Lotto(changeStringToList(winningInput));
    }

    private WinningLotto makeWinningLotto() {
        String winningInput = LottoView.requestWinningNumber();
        Lotto winLotto = new Lotto(changeStringToList(winningInput));
        String bonusInput = LottoView.requestBonusBallNumber();
        return new WinningLotto(winLotto, bonusInput);
    }

    private ArrayList<String> changeStringToList(String numberInput) {
        return Arrays.stream(numberInput.split(DELIMITER))
                .map(String::trim)
                .collect(Collectors.toCollection(ArrayList::new));
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