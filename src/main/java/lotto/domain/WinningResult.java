package lotto.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class WinningResult {
    private static final Map<Rank, Integer> countIndexMapper = new HashMap<>();
    private int[] rankCounter = new int[5];

    static {
        countIndexMapper.put(Rank.FIRST, 4);
//        countIndexMapper.put(Rank.SECOND, 3);
        countIndexMapper.put(Rank.THIRD, 3);
        countIndexMapper.put(Rank.FOURTH, 2);
        countIndexMapper.put(Rank.FIFTH, 1);
        countIndexMapper.put(Rank.MISS, 0);
    }

    public WinningResult() {
        Arrays.fill(rankCounter, 0);
    }

    public void count(Lotto targetLotto, Lotto winningLotto) {
        rankCounter[countIndexMapper.get(Rank.valueOf(winningLotto.findCountOfMatchNo(targetLotto)))]++;
    }

    public int[] getRankCounter() {
        return rankCounter;
    }

    public int getSpendMoney() {
        return Arrays.stream(rankCounter).sum() * LottoSeller.LOTTO_PRICE;
    }

    public int getWinningMoney() {
        int winningMoney = 0;
        for (Rank rank : countIndexMapper.keySet()) {
            winningMoney += (rank.getWinningMoney() * rankCounter[countIndexMapper.get(rank)]);
        }
        return winningMoney;
    }
}