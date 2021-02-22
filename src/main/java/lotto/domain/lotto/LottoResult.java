package lotto.domain.lotto;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class LottoResult {

    private static final int RATE = 100;
    private final Map<LottoRank, Integer> rank;

    public LottoResult() {
        this.rank = new EnumMap<>(LottoRank.class);
        Arrays.stream(LottoRank.values()).forEach(level -> rank.put(level, 0));
    }

    public Map getResult() {
        return this.rank;
    }

    public void add(Lotto lotto, WinningLotto winningLotto) {
        int count = (int) lotto.getNumbers().stream()
            .filter(number -> winningLotto.getLotto().getNumbers().contains(number)).count();
        boolean bonus = lotto.getNumbers().contains(winningLotto.getBonus());
        LottoRank lottoRank = findRank(count, bonus);
        rank.put(lottoRank, rank.get(lottoRank) + 1);
    }

    public LottoRank findRank(int count, boolean bonus) {
        return LottoRank.checkRank(count, bonus);
    }

    public double getProfitRate() {
        return Math.floor((double) winningPrice() / purchasePrice() * RATE) / RATE;
    }

    private Long purchasePrice() {
        long numLotto = rank.values().stream().reduce(0, Integer::sum);
        return numLotto * LottoStore.LOTTO_PRICE;
    }

    private Long winningPrice() {
        return rank.entrySet()
            .stream()
            .mapToLong(entrySet -> (long) entrySet.getKey().getWinningMoney() * entrySet.getValue())
            .sum();
    }

    public Map<LottoRank, Integer> getRank() {
        return this.rank;
    }
}