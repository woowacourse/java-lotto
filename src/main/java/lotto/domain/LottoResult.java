package lotto.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class LottoResult {

  private Map<LottoRank, Integer> rankMatch;
  // 기능을 완료하려면 몇 개가 일치한지 카운트가 되야된다.
  // 가격만 구할 수 있는 기능만 존재
  // Map 기능을 사용해야 하지 않을까 생각이 듭니다.
  // Map Rank - count
  // Rank - 0을 셋팅해줘야될 것 같아요.

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
