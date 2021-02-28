package lotto.domain;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;
import lotto.utils.CustomException;
import lotto.utils.StringChecker;

public class Money implements Comparable<Money> {
    private static final int DECIMAL_POINT = 2;
    private static final int PERCENT = 100;
    private final BigDecimal money;

    private Money(long value) {
        validateNotNegative(value);
        this.money = BigDecimal.valueOf(value);
    }

    private Money(String moneyValue) {
        validateIsNumber(moneyValue);
        this.money = BigDecimal.valueOf(Long.parseLong(moneyValue));
    }

    private Money(BigDecimal moneyValue) {
        this.money = moneyValue;
    }

    private void validateNotNegative(long value) {
        if (value < 1) {
            throw new CustomException("돈의 크기는 1이상이어야 합니다.");
        }
    }

    private void validateIsNumber(String moneyValue) {
        if (!StringChecker.isNumber(moneyValue)) {
            throw new CustomException("구입금액은 숫자이어야 합니다.");
        }
    }

    public static Money valueOf(long i) {
        return new Money(i);
    }

    public static Money valueOf(String i) {
        return new Money(i);
    }

    public int toInt() {
        return this.money.intValue();
    }

    public Money getChange(int analogCount, int autoCount, Money lottoPrice) {
        final Money analog = lottoPrice.multiply(analogCount);
        final Money auto = lottoPrice.multiply(autoCount);
        final BigDecimal totalSpent = analog.add(auto).toBigDecimal();
        final BigDecimal result = this.money.subtract(totalSpent);
        return new Money(result);
    }

    public Money add(Money value) {
        return new Money(money.add(value.toBigDecimal()));
    }

    public Money subtract(Money value) {
        return new Money(money.subtract(value.toBigDecimal()));
    }

    public Money divide(Money value) {
        return new Money(
            money.divide(value.toBigDecimal(), DECIMAL_POINT, BigDecimal.ROUND_CEILING));
    }


    public Money multiply(Money value) {
        return new Money(money.multiply(value.toBigDecimal()));
    }

    public Money multiply(int intValue) {
        return new Money(money.multiply(BigDecimal.valueOf(intValue)));
    }


    public BigInteger getEarningRate(Map<Rank, Integer> results) {
        final Money totalPrize = calculateTotalPrize(results);
        return calculateEarningRate(this, totalPrize);

    }

    public Money getMoneySpentForTicket(Money lottoPrice, int value) {
        return lottoPrice.multiply(value);
    }

    private Money calculateTotalPrize(Map<Rank, Integer> resultMap) {
        BigDecimal localPrize = BigDecimal.ZERO;

        for (Map.Entry<Rank, Integer> result : resultMap.entrySet()) {
            localPrize = localPrize.add(
                result.getKey().getPrize()
                    .multiply(BigDecimal.valueOf(result.getValue()))
            );
        }
        return new Money(localPrize);
    }

    private BigInteger calculateEarningRate(Money inputMoney, Money totalPrize) {
        return totalPrize
            .divide(inputMoney)
            .multiply(Money.valueOf(PERCENT)).toBigInteger();
    }

    private BigInteger toBigInteger() {
        return money.toBigInteger();
    }

    private BigDecimal toBigDecimal() {
        return this.money;
    }

    @Override
    public int compareTo(Money o) {
        return money.compareTo(o.toBigDecimal());
    }

}
