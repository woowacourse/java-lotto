package lotto.domain;

public class LottoPurchaseMoney {

    private static final int PRICE_CRITERION = 1000;

    private final int price;

    private LottoPurchaseMoney(int price) {
        validateCriterion(price);
        this.price = price;
    }

    public static LottoPurchaseMoney create(String value) {
        int price = translateInteger(value);

        return new LottoPurchaseMoney(price);
    }

    public int calculate() {
        return this.price / PRICE_CRITERION;
    }

    public int getPrice() {
        return this.price;
    }

    private static int translateInteger(String value) {
        try {
            return Integer.parseInt(value);
        } catch(NumberFormatException e) {
            throw new IllegalArgumentException("숫자를 입력해주세요.");
        }
    }

    private void validateCriterion(int price) {
        if (price < PRICE_CRITERION) {
            throw new IllegalArgumentException("티켓은 1000원 입니다. 1000원 이상 입력해주세요.");
        }
    }
}
