package domain;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public enum WinningCase {

  ELSE(0, 0, false),
  THREE_SAME(5_000, 3, false),
  FOUR_SAME(50_000, 4, false),
  FIVE_SAME(1_500_000, 5, false),
  FIVE_BONUS_SAME(30_000_000, 5, true),
  SIX_SAME(2_000_000_000, 6, false);

  private final int winningMoney;
  private final int sameCount;
  private final boolean bonusCase;

  WinningCase(int winningMoney, int sameCount, boolean bonusCase) {
    this.winningMoney = winningMoney;
    this.sameCount = sameCount;
    this.bonusCase = bonusCase;
  }

  public static WinningCase getWinningCase(int sameCount, boolean isBonus) {
    return Arrays.stream(WinningCase.values()).filter((o) ->
        o.sameCount == sameCount && isBonus == o.bonusCase
    ).findFirst().orElse(ELSE);
  }

  public static Map<WinningCase, Integer> toMap() {
    Map<WinningCase, Integer> winningCaseIntegerMap = new LinkedHashMap<>();
    for (WinningCase winningCase : WinningCase.values()) {
      winningCaseIntegerMap.put(winningCase, 0);
    }
    return winningCaseIntegerMap;
  }

  public long calculateEarnMoney(int winningCaseCount) {
    return  (long) winningCaseCount * winningMoney;
  }

  public int getWinningMoney() {
    return winningMoney;
  }

  public int getSameCount() {
    return sameCount;
  }
}
