package validator;

public class MoneyValidator {

    public static final String MONEY_OVER_THOUSANDS_ERROR_MESSAGE = "로또 구입 금액은 1000원 이상이어야 합니다.";
    public static final String MONEY_DIVIDE_ERROR_MESSAGE = "로또 구입 금액은 1000 단위여야 합니다.";

    public static void validate(int money) {
        isOverThousand(money);
        isDivideByThousand(money);
    }

    private static void isOverThousand(int money) {
        if (money < 1000) {
            throw new IllegalArgumentException(MONEY_OVER_THOUSANDS_ERROR_MESSAGE);
        }
    }

    private static void isDivideByThousand(int money) {
        if (money % 1000 != 0) {
            throw new IllegalArgumentException(MONEY_DIVIDE_ERROR_MESSAGE);
        }
    }
}