package lotto.domain;

import lotto.exception.LottoPriceException;

public class Money {

  private final long money;

  private Money(final long price) {
    if (price < 0) {
      throw new LottoPriceException("음수를 입력할 수 없습니다.");
    }
    this.money = price;
  }

  public static Money of(final int price) {
    return new Money(price);
  }

  public int divide(final int number) {
    return (int) money / number;
  }
}
