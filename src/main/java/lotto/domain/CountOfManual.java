package lotto.domain;

public class CountOfManual {
    private final int countOfManual;

    private CountOfManual(final int countOfManual, final int countOfPurchase) {
        validate(countOfManual, countOfPurchase);
        this.countOfManual = countOfManual;
    }

    public static CountOfManual of(final int countOfManual, final int countOfPurchase) {
        return new CountOfManual(countOfManual, countOfPurchase);
    }

    private void validate(final int countOfManual, final int countOfPurchase) {
        if (countOfManual < 0) {
            throw new IllegalArgumentException("수동 구매 횟수 음수 입력은 불가능합니다");
        }
        if (countOfManual < countOfPurchase) {
            throw new IllegalArgumentException("수동 구매 횟수는 총 구매 횟수보다 많으면 안됩니다.");
        }
    }

    public int value() {
        return countOfManual;
    }
}
