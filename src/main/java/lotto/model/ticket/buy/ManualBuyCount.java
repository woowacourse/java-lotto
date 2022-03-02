package lotto.model.ticket.buy;

public class ManualBuyCount {
    public static final int MIN_BUY_COUNT = 0;
    private int count;

    private ManualBuyCount(int count) {
        validate(count);
        this.count = count;
    }

    public static ManualBuyCount of(int count) {
        return new ManualBuyCount(count);
    }

    private void validate(int count) {
        if (count < MIN_BUY_COUNT) {
            throw new IllegalArgumentException("수동으로 구매할 로또 수는 0이상의 수여야 합니다");
        }
    }
}
