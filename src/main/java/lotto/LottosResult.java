package lotto;

import java.util.EnumMap;

import static lotto.Money.ONE_LOTTO_PRICE;

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
        int numberOfLotto = INITIAL_NUMBER;
        long totalWinnigMoney = INITIAL_NUMBER;

        for (Rank rank : Rank.values()) {
            int numberOfRank = lottosResult.get(rank);

            numberOfLotto += numberOfRank;
            totalWinnigMoney += rank.getWinningMoney() * numberOfRank;
        }
        return (double) totalWinnigMoney / (numberOfLotto * ONE_LOTTO_PRICE);
    }
}
