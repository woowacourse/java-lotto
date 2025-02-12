package view;

public class InputValidator {

    public void validateInputMoney(String money) {
        validateNotStringNumber(money);
    }

    public void validateWinningNumber(String rawWinningNumbers) {
        String[] values = rawWinningNumbers.split(",");
        for (String value : values) {
            validateNotStringNumber(value);
        }
    }

    private void validateNotStringNumber(String money) {
        try {
            Integer.parseInt(money);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("허용되지 않는 입력입니다.", e);
        }
    }
}
