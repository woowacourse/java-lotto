package validator;

public class MoneyValidator {

    public static final String MONEY_OVER_THOUSANDS_ERROR_MESSAGE = "로또 구입 금액은 1000원 이상이어야 합니다.";
    public static final String MONEY_DIVIDE_ERROR_MESSAGE = "로또 구입 금액은 1000 단위여야 합니다.";
    public static final int DIVIDE_UNIT = 1000;

    public static void validate(final int money) {
        isOverThousand(money);
        isDivideByThousand(money);
    }

    private static void isOverThousand(final int money) {
        if (money < DIVIDE_UNIT) {
            throw new IllegalArgumentException(MONEY_OVER_THOUSANDS_ERROR_MESSAGE);
        }
    }

    private static void isDivideByThousand(final int money) {
        if (money % DIVIDE_UNIT != 0) {
            throw new IllegalArgumentException(MONEY_DIVIDE_ERROR_MESSAGE);
        }
    }
}