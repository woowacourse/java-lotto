package lotto.model;

public class Money {

    private final int amount;

    public Money(final int amount) {
        validateLessThanZero(amount);
        this.amount = amount;
    }

    private void validateLessThanZero(final int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("금액은 0원 미만일 수 없습니다.");
        }
    }

    public int calculateBuyingCount(final int unitPrice) {
        return amount / unitPrice;
    }

    public double calculateReturnRatio(final long totalReturnMoney) {
        double returnRatio = (double) totalReturnMoney / amount;
        return Math.floor(returnRatio * 100) / 100;
    }

}
