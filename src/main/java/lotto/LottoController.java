package lotto;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class LottoController {
    private static Lottos lottos;
    private static WinningLotto winningLotto;
    private static ArrayList<Rank> wins = new ArrayList<>();
    private static Map<Rank, Integer> countByRank = new HashMap<>();

    public void startLotto() {
        lottos = new Lottos(LottoView.requestMoney());
        LottoView.buyLotto(lottos.getCount());
        LottoView.printLottos(lottos);
        makeWinningLotto();
    }

    public void makeWinningLotto() {
        String winningInput = LottoView.requestWinningNumber();
        String bonusInput = LottoView.requestBonusBallNumber();
        winningLotto = new WinningLotto(winningInput, bonusInput);
    }

    public void drawLotto() {
        ArrayList<Lotto> lottoGroup = lottos.getLottoGroup();
        for (Lotto lotto : lottoGroup) {
            Rank rank = winningLotto.findRank(lotto);
            wins.add(rank);
        }
    }

    public static void drawResult() {
        LottoView.displayResultMessage();
        countEachRank();
        countByRank.forEach((rank, rankCount) ->
            LottoView.displayResult(rank, rankCount));
    }

    private static void countEachRank() {
        for (int i = 1; i < Rank.values().length; i++) {
            Rank rank = Rank.values()[i];
            int rankCount = (int) wins.stream().filter(win -> win == rank).count();
            countByRank.put(rank, rankCount);
        }
    }
}
