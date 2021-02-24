package lotto.domain;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;
import lotto.utils.CustomException;
import lotto.utils.StringChecker;

public class Money {
    private final BigDecimal money;
    private final int ticketCount;
    private final BigDecimal change;

    public Money(String moneyValue) {
        this(moneyValue, 0);
    }

    public Money(String moneyValue, int analogTicketCount) {
        validateIsNumber(moneyValue);
        validateNotOverMoney(moneyValue, analogTicketCount);
        this.money = new BigDecimal(moneyValue);
        this.ticketCount = money.divideToIntegralValue(new BigDecimal(LottoTicket.PRICE)).intValue()
            - analogTicketCount;
        this.change = money
            .subtract(
                BigDecimal.valueOf((long) (analogTicketCount + ticketCount) * LottoTicket.PRICE));
    }

    private void validateNotOverMoney(String moneyValue, int analogTicketCount) {
        if (Integer.parseInt(moneyValue) < analogTicketCount * LottoTicket.PRICE) {
            throw new CustomException("수동발행이 구입가능금액을 넘어 발행이 취소됩니다.");
        }
    }

    private void validateIsNumber(String moneyValue) {
        if (!StringChecker.isNumber(moneyValue)) {
            throw new CustomException("구입금액은 숫자이어야 합니다.");
        }
    }

    public int getPossibleTicketCount() {
        return ticketCount;
    }

    public int getChange() {
        return change.intValue();
    }

    private BigDecimal calculateTotalPrize(Map<Rank, Integer> resultMap) {
        BigDecimal localPrize = BigDecimal.ZERO;

        for (Map.Entry<Rank, Integer> result : resultMap.entrySet()) {
            localPrize = localPrize.add(
                result.getKey().getPrize()
                    .multiply(BigDecimal.valueOf(result.getValue()))
            );
        }
        return localPrize;
    }

    private BigInteger calculateEarningRate(BigDecimal buyPrice, BigDecimal totalPrize) {
        return (totalPrize
            .divide(buyPrice, 2, BigDecimal.ROUND_CEILING))
            .multiply(BigDecimal.valueOf(100)).toBigInteger();
    }

    public BigInteger getEarningRate(Map<Rank, Integer> results) {
        final BigDecimal totalPrize = calculateTotalPrize(results);
        return calculateEarningRate(money.subtract(change), totalPrize);

    }

}
