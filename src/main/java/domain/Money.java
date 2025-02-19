package domain;

import exception.LottoException;

public class Money {

  private final int MONEY_UNIT = 1000;
  private final String INVALID_MONEY = "유효하지 않은 금액입니다.";

  private final int money;

  public Money(int money) {
    validateMoney(money);
    this.money = money;
  }

  public int calculateBuyLottoAmount() {
    return money / MONEY_UNIT;
  }

  public double calculateEarnMoneyRatio(long earnMoney) {
    return (double) earnMoney / money;
  }

  private void validateMoney(int buyMoney) {
    validateZeroMoney(buyMoney);
    validateUnit(buyMoney);
  }

  private void validateZeroMoney(int buyMoneyNumber) {
    if (buyMoneyNumber == 0) {
      throw new LottoException(INVALID_MONEY);
    }
  }

  private void validateUnit(int buyMoneyNumber) {
    if (buyMoneyNumber % MONEY_UNIT != 0) {
      throw new LottoException(INVALID_MONEY);
    }
  }

}
