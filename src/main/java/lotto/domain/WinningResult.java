package lotto.domain;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.WinningLotto;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WinningResult {
    private static final Map<Rank, Integer> countIndexMapper = new HashMap<>();

    static {
        countIndexMapper.put(Rank.FIRST, 5);
        countIndexMapper.put(Rank.SECOND, 4);
        countIndexMapper.put(Rank.THIRD, 3);
        countIndexMapper.put(Rank.FOURTH, 2);
        countIndexMapper.put(Rank.FIFTH, 1);
        countIndexMapper.put(Rank.MISS, 0);
    }

    private int[] rankCounter = new int[6];

    public WinningResult(List<Lotto> lottos, WinningLotto winningLotto) {
        Arrays.fill(rankCounter, 0);

        for (Lotto lotto : lottos) {
            count(lotto, winningLotto);
        }
    }

    private void count(Lotto targetLotto, WinningLotto winningLotto) {
        rankCounter[countIndexMapper.get(Rank.valueOf(winningLotto.findCountOfMatchNo(targetLotto), winningLotto.checkBonusNoIn(targetLotto)))]++;
    }

    public int[] getRankCounter() {
        return rankCounter;
    }

    public int getSpendMoney() {
        return Arrays.stream(rankCounter).sum() * Lotto.LOTTO_PRICE;
    }

    public BigInteger getWinningMoney() {
        BigInteger winningMoney = BigInteger.valueOf(0);
        for (Rank rank : countIndexMapper.keySet()) {
            winningMoney = winningMoney.add(BigInteger.valueOf(rank.getWinningMoney())
                    .multiply(BigInteger.valueOf(rankCounter[countIndexMapper.get(rank)])));
        }
        return winningMoney;
    }
}