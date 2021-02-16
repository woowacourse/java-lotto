package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LottoResult {

  private List<LottoRank> ranks;

  public LottoResult() {
    this.ranks = new ArrayList<>();
  }

  public void add(LottoRank lottoRank) {
    ranks.add(lottoRank);
  }

  public double winningProfit() {
    return Math.round((double) totalWinningAmount() / totalPrice() * 100);
  }

  private Long totalPrice() {
    return (long) ranks.size() * LottoSeller.lottoPrice();
  }

  private Long totalWinningAmount() {
    return ranks.stream()
        .mapToLong(LottoRank::winningMoney)
        .sum();
  }

  public List<LottoRank> get() {
    return Collections.unmodifiableList(ranks);
  }
}
