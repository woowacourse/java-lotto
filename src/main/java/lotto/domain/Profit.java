package lotto.domain;

public record Profit(double rate, boolean isProfit) {

    public static Profit from(long winningPrize, Money spentMoney) {
        if (spentMoney.amount() <= 0) {
            throw new IllegalArgumentException("구입 금액이 0 이하일 수 없습니다.");
        }
        double rate = (double) winningPrize / spentMoney.amount();
        boolean isProfit = rate >= 1.0;

        return new Profit(rate, isProfit);
    }
}
