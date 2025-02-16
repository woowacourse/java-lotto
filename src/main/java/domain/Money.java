package domain;

import exception.LottoException;
import utility.StringUtility;

public class Money {

  private final int MONEY_UNIT = 1000;
  private final String INVALID_MONEY = "유효하지 않은 금액입니다.";

  private final int money;

  public Money(String money) {
    validateMoney(money);
    this.money = Integer.parseInt(money);
  }

  public int calculateBuyLottoAmount() {
    return money / MONEY_UNIT;
  }

  public int calculateBuyLottoCount() {
    return calculateBuyLottoAmount();
  }

  public double calculateEarnMoneyRatio(long earnMoney) {
    return  (double) earnMoney / money;
  }

  private void validateMoney(String moneyInput) {
    validateNumeric(moneyInput);
    int buyMoneyNumber = Integer.parseInt(moneyInput);
    validateZeroMoney(buyMoneyNumber);
    validateUnit(buyMoneyNumber);
  }

  private void validateNumeric(String moneyInput) {
    if(moneyInput == null || !StringUtility.isNumber(moneyInput)){
      throw new LottoException(INVALID_MONEY);
    }
  }

  private void validateZeroMoney(int buyMoneyNumber) {
    if(buyMoneyNumber == 0){
      throw new LottoException(INVALID_MONEY);
    }
  }

  private void validateUnit(int buyMoneyNumber) {
    if(buyMoneyNumber % MONEY_UNIT != 0){
      throw new LottoException(INVALID_MONEY);
    }
  }

}
