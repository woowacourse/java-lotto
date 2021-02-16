package lotto.domain;

import java.util.Arrays;

public enum LottoRank {
  FIRST(6, 2_000_000_000),
  SECOND(5, 30_000_000),
  THIRD(5, 1_500_000),
  FOURTH(4, 50_000),
  FIFTH(3, 5_000),
  NONE(0, 0);

  private static final int MIN_MATCH = 3;
  private static final String MESSAGE_FORM = "%d개 일치 (%d원)- %개";

  private final int matchCount;
  private final int winningMoney;

  LottoRank(int matchCount, int winningMoney) {
    this.matchCount = matchCount;
    this.winningMoney = winningMoney;
  }

  public static LottoRank of(int matchCount, boolean bonusMatch) {
    if (matchCount < MIN_MATCH) {
      return NONE;
    }

    if (THIRD.matchCount == matchCount && !bonusMatch) {
      return THIRD;
    }

    return Arrays.stream(values())
        .filter(value -> value.matchCount == matchCount)
        .findFirst()
        .orElseThrow(() -> new IllegalStateException("값이 잘못되었습니다."));
  }

  public int winningMoney() {
    return winningMoney;
  }

  public String message(int count) {
    return String.format(MESSAGE_FORM, matchCount, winningMoney, count);
  }

}
