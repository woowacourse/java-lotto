package lotto.domain;

public class LottoCalculator {

    public static double divide(final long money, final long winningMoney) {
        return (double) winningMoney / (double) money;
    }
}