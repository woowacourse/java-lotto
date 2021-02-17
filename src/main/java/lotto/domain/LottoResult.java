package lotto.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class LottoResult {

  private Map<LottoRank, Integer> rankMatch;

  public LottoResult() {
    this.rankMatch = new EnumMap<>(LottoRank.class);
    initRankMatch();
  }

  public void initRankMatch() {
    Arrays.stream(LottoRank.values())
        .forEach(rank -> rankMatch.put(rank, 0));
  }

  public void add(LottoRank lottoRank) {
    rankMatch.put(lottoRank, rankMatch.get(lottoRank) + 1);
  }

  public double winningProfit() {
    return Math.round((double) totalWinningAmount() / totalPrice() * 100);
  }

  private Long totalPrice() {
    long count = rankMatch
        .values()
        .stream()
        .reduce(0, Integer::sum);
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
