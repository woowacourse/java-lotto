package lotto.domain;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import lotto.domain.lotto.WinningLotto;

import java.util.EnumMap;
import java.util.Map;

import static lotto.domain.Money.ONE_LOTTO_PRICE;

public class LottosResult {
    private static final int INITIAL_NUMBER = 0;

    private final Map<Rank, Integer> lottosResult = new EnumMap<>(Rank.class);

    public LottosResult(WinningLotto winningLotto, Lottos myLottos) {
        for (Lotto lotto : myLottos.getLottos()) {
            Rank rank = winningLotto.matchRank(lotto);
            lottosResult.put(rank, lottosResult.getOrDefault(rank, 0) + 1);
        }
    }

    public int valueOf(Rank rank) {
        return lottosResult.getOrDefault(rank, 0);
    }

    public double getROI() {
        long totalWinnigMoney = getWinningMoney();
        int numberOfLotto = lottosResult.values().stream().reduce(INITIAL_NUMBER, Integer::sum);

        return (double) totalWinnigMoney / (numberOfLotto * ONE_LOTTO_PRICE);
    }

    private long getWinningMoney() {
        long totalWinnigMoney = INITIAL_NUMBER;

        for (Rank rank : Rank.values()) {
            totalWinnigMoney += rank.getWinningMoney() * lottosResult.getOrDefault(rank, 0);
        }
        return totalWinnigMoney;
    }
}
