package lotto.domain;

import java.util.Objects;

public class Money {

    private static final String MONEY_LIMIT_EXCEPTOIN_MESSAGE = "1,000원 이상 100,000원 미만의 금액을 입력해주세요.";
    private static final String MONEY_EXCHANGE_EXCEPTION_MESSAGE = "1,000원 단위로 입력해주세요.";
    private static final int MAX_PURCHASE_PRICE = 100000;
    private static final int MIN_PURCHASE_PRICE = 1000;
    private static final int LOTTO_PRICE = 1000;
    private int money;

    public Money(int inputMoney) {
        checkMoneyLimitRange(inputMoney);
        checkExchage(inputMoney);
        this.money = inputMoney;
    }

    private void checkExchage(int inputMoney) {
        if (inputMoney % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(MONEY_EXCHANGE_EXCEPTION_MESSAGE);
        }
    }

    private void checkMoneyLimitRange(int inputMoney) {
        if (inputMoney < MIN_PURCHASE_PRICE || inputMoney > MAX_PURCHASE_PRICE) {
            throw new IllegalArgumentException(MONEY_LIMIT_EXCEPTOIN_MESSAGE);
        }
    }

    public int availablePurchseTicketCount() {
        return money / LOTTO_PRICE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money1 = (Money) o;
        return money == money1.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }
}
