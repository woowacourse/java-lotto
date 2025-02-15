package lotto.model;

public class Money {
    public static final int UNIT_PRICE = 1_000;

    private final long buyingMoney;

    public Money(final long buyingMoney) {
        validate(buyingMoney);
        this.buyingMoney = buyingMoney;
    }

    private void validate(final long buyingMoney) {
        validateRange(buyingMoney);
        validateUnit(buyingMoney);
    }

    private void validateRange(final long buyingMoney) {
        if (buyingMoney < 0) {
            throw new IllegalArgumentException("알맞은 형식으로 입력해주세요.");
        }
    }

    private void validateUnit(final long buyingMoney) {
        if (buyingMoney % UNIT_PRICE != 0) {
            throw new IllegalArgumentException("천원 단위로 입력해주세요.");
        }
    }

    public double calculateReturnRatio(final long earnedMoney) {
        double ratio = (double) earnedMoney / this.buyingMoney;
        return Math.floor(ratio * 100) / 100;
    }

    public long getBuyingMoney() {
        return buyingMoney;
    }
}
