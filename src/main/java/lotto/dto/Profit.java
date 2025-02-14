package lotto.dto;

public record Profit(double rate, boolean isProfit) {

    public static Profit from(long winningPrize, long spentMoney) {
        if (spentMoney <= 0) {
            throw new IllegalArgumentException("구입 금액이 0 이하일 수 없습니다.");
        }
        double rate = (double) winningPrize / spentMoney;
        boolean isProfit = rate >= 1.0;

        return new Profit(rate, isProfit);
    }
}
