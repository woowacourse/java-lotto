package lotto.domain;

import java.util.Objects;

/**
 * @author heebg
 * @version 1.0 2019-06-03
 */
public class Money {
    private static final transient long MIN_MONEY_RANGE = 1000;
    private static final transient long ONE_LOTTO_PRICE = MIN_MONEY_RANGE;
    private static final transient String EX_MONEY_MESSAGE = "로또 한장의 가격은 " + MIN_MONEY_RANGE + "원입니다.";
    private static final transient String EX_MANUAL_COUNT_RANGE_MESSAGE = "0 이상의 구입 금액을 초과하지 않는 숫자를 입력해주세요(로또는 한장에 " + ONE_LOTTO_PRICE + "원입니다.)";
    private final long money;
    private final long count;

    public Money(long money) {
        this.money = money;
        this.count = generateLottoBuyCount();
        checkMoneyCondition();
    }

    public long calculateAutoCount(long manualCount) {
        checkManualCount(manualCount);
        return (money - manualCount * ONE_LOTTO_PRICE) / ONE_LOTTO_PRICE;
    }

    private long generateLottoBuyCount() {
        return money / ONE_LOTTO_PRICE;
    }

    private void checkMoneyCondition() {
        if (money < MIN_MONEY_RANGE) {
            throw new IllegalArgumentException(EX_MONEY_MESSAGE);
        }
    }

    public void checkManualCount(long manualCount) {
        if (manualCount > count || manualCount < 0) {
            throw new IllegalArgumentException(EX_MANUAL_COUNT_RANGE_MESSAGE);
        }
    }

    public float calculateRate(long jackpot) {
        return ((float) jackpot / (float) money);
    }

    public long getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "{" +
                "money:" + money +
                ", count:" + count +
                '}';
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
