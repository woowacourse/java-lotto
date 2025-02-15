package view;

public class InputValidator {

    public void validateInputMoney(String money) {
        validateNotStringNumber(money);
    }

    public void validateNotStringNumber(String value) {
        try {
            Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("허용되지 않는 입력입니다.", e);
        }
    }
}
