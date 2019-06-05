package lotto.domain;

import java.util.EnumMap;

import static lotto.domain.Money.ONE_LOTTO_PRICE;

public class LottosResult {
    private static final int INITIAL_NUMBER = 0;

    private final EnumMap<Rank, Integer> lottosResult = new EnumMap<>(Rank.class);

    public LottosResult(WinningLotto winningLotto, Lottos myLottos) {
        setUpResult();

        for (Lotto lotto : myLottos.getLottos()) {
            Rank rank = winningLotto.matchRank(lotto);
            lottosResult.put(rank, lottosResult.get(rank) + 1);
        }
    }

    private void setUpResult() {
        for (Rank rank : Rank.values()) {
            lottosResult.put(rank, INITIAL_NUMBER);
        }
    }

    public double getROI() {
        long totalWinnigMoney = getWinningMoney();
        int numberOfLotto = lottosResult.values().stream().reduce(INITIAL_NUMBER, Integer::sum);

        return (double) totalWinnigMoney / (numberOfLotto * ONE_LOTTO_PRICE);
    }

    private long getWinningMoney() {
        long totalWinnigMoney = INITIAL_NUMBER;

        for (Rank rank : Rank.values()) {
            totalWinnigMoney += rank.getWinningMoney() * lottosResult.get(rank);
        }
        return totalWinnigMoney;
    }
}
