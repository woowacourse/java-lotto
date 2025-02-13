package domain;

import static util.constant.Message.PRICE_NEGATIVE_ERROR;
import static util.constant.Values.LOTTO_UNIT;

import java.util.HashMap;

public class Money {

  private int money;

  public Money(int money) {
    validateRange(money);
    this.money = money;
  }

  public int calculateTotalLotto() {
    return money / LOTTO_UNIT;
  }

  private void validateRange(int money) {
    if (money < 0) {
      throw new IllegalArgumentException(PRICE_NEGATIVE_ERROR);
    }
  }

  public double calculateProfit(HashMap<LottoMatch, Integer> lottoResult) {
    int sum = 0;
    for (LottoMatch lottoMatch : lottoResult.keySet()) {
      sum += lottoMatch.prize * lottoResult.get(lottoMatch);
    }
    return (double) sum / money;
  }
}
