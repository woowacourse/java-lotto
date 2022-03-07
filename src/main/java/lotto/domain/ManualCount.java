package lotto.domain;

public class ManualCount {

    private final int count;

    public ManualCount(int count) {
        this.count = count;
    }

    public static ManualCount of(Money money, int count) {
        validate(money, count);
        return new ManualCount(count);
    }

    private static void validate(Money money, int count) {
        validatePositive(count);
        validateLessThenMoney(money, count);
    }

    private static void validatePositive(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("수동 구매 개수는 0이상의 정수만 가능합니다.");
        }
    }

    private static void validateLessThenMoney(Money money, int count) {
        if (money.count() < count) {
            throw new IllegalArgumentException("수동 구매 개수는 총 구매 개수 이내에서만 구매 가능합니다.");
        }
    }

    public int getCount() {
        return count;
    }
}
