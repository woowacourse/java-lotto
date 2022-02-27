package domain;

public class UserBalance {
    private static final String INVALID_BALANCE_INPUT_EXCEPTION_MESSAGE = "구입금액은 숫자여야 합니다.";
    private static final String INVALID_CURRENCY_EXCEPTION_MESSAGE = "구입금액은 1000원 이상이어야 하며 1000원 미만일 수 없습니다.";

    private final int userBalance;

    public UserBalance(String value) {
        this.userBalance = validateUserBalance(value);
    }

    private int validateUserBalance(String value) {
        int balance = parseNumber(value);
        validateBalance(balance);
        return balance;
    }

    private int parseNumber(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_BALANCE_INPUT_EXCEPTION_MESSAGE);
        }
    }

    private void validateBalance(int balance) {
        if (!(balance >= 1000 && balance % 1000 == 0)) {
            throw new IllegalArgumentException(INVALID_CURRENCY_EXCEPTION_MESSAGE);
        }
    }

    public int getUserBalance() {
        return userBalance;
    }
}
