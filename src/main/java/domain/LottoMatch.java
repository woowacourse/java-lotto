package domain;

import java.util.Arrays;

public enum LottoMatch {
  DEFUALT_MATCH(0,false,0),
  THREE_MATCH(3, false, 5000),
  FOUR_MATCH(4, false, 50000),
  FIVE_MATCH(5, false, 1500000),
  FIVE_BONUS_MATCH(5, true, 30000000),
  SIX_MATCH(6, false, 2000000000);

  public int winningCounter;
  public boolean bonusChecker;
  public int prize;

  private LottoMatch(int winningCounter, boolean bonusChecker, int prize) {
    this.winningCounter = winningCounter;
    this.bonusChecker = bonusChecker;
    this.prize = prize;
  }

  public static LottoMatch calculateLotto(int winningCounter, boolean bonusChecker ){
    return Arrays.stream(values()).filter(lottoMatch -> lottoMatch.winningCounter == winningCounter
        && lottoMatch.bonusChecker == bonusChecker).findFirst().orElse(DEFUALT_MATCH);
  }

  @Override
  public String toString() {
    String result = "";
    result += String.valueOf(winningCounter) + "개 일치 ";

    if(bonusChecker) {
      result += ", 보너스 볼 일치 ";
    }

    result += "(" + prize + ")";
    return result;
  }
}
