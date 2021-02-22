package lotto.domain.lotto;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class LottoResult {

    private static final int RATE = 100;
    private final Map<LottoRank, Integer> rankRepository;

    public LottoResult() {
        this.rankRepository = new EnumMap<>(LottoRank.class);
        Arrays.stream(LottoRank.values()).forEach(level -> rankRepository.put(level, 0));
    }

    public void checkWinningLotto(Lotto lotto, WinningLotto winningLotto) {
        LottoRank rank = lotto.check(winningLotto);
        rankRepository.put(rank, rankRepository.get(rank) + 1);
    }

    public double getProfitRate() {
        return Math.floor((double) winningPrice() / purchasePrice() * RATE) / RATE;
    }

    private Long purchasePrice() {
        long numLotto = rankRepository.values().stream().reduce(0, Integer::sum);
        return numLotto * LottoStore.LOTTO_PRICE;
    }

    private Long winningPrice() {
        return rankRepository.entrySet()
            .stream()
            .mapToLong(entrySet -> (long) entrySet.getKey().getWinningMoney() * entrySet.getValue())
            .sum();
    }

    public Map<LottoRank, Integer> getResult() {
        return this.rankRepository;
    }
}