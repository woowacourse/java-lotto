package lotto.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import lotto.domain.LottoRank;

public class LottoResult {

  private static final int INIT_COUNT = 0;
  private static final int COUNTING_NUMBER = 1;
  private static final int RATE = 100;
  private final Map<LottoRank, Integer> rankMatch;

  public LottoResult() {
    this.rankMatch = new EnumMap<>(LottoRank.class);
    initRankMatch();
  }

  private void initRankMatch() {
    Arrays.stream(LottoRank.values())
        .forEach(rank -> rankMatch.put(rank, INIT_COUNT));
  }

  public void add(final LottoRank lottoRank) {
    rankMatch.put(lottoRank, rankMatch.get(lottoRank) + COUNTING_NUMBER);
  }

  public double winningProfit() {
    return Math.round((double) totalWinningAmount() / totalPrice() * RATE);
  }

  private Long totalPrice() {
    long count = rankMatch
        .values()
        .stream()
        .reduce(INIT_COUNT, Integer::sum);
    return count * LottoSeller.lottoPrice();
  }

  private Long totalWinningAmount() {
    return rankMatch.entrySet()
        .stream()
        .mapToLong(entrySet -> (long) entrySet.getKey().winningMoney() * entrySet.getValue())
        .sum();
  }

  public Map<LottoRank, Integer> rankMatch() {
    return Collections.unmodifiableMap(rankMatch);
  }
}
