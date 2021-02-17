package lotto.domain;

public class LottoCalculator {

    public static double divide(long money, long winningMoney) {
        return (double) winningMoney / (double) money;
    }
}