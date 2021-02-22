package lotto.domain;

import lotto.exception.LottoPriceException;

public class Money {

  private final long value;

  private Money(final long value) {
    if (value < 0) {
      throw new LottoPriceException("돈이 부족합니다.");
    }
    this.value = value;
  }

  public static Money of(final int price) {
    return new Money(price);
  }

  public Money minus(final int price) {
    return new Money(value - price);
  }

  public int affordableCount(final int price) {
    return (int) value / price;
  }

  public long value() {
    return value;
  }
}
