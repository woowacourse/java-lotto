package view;

public class InputValidator {

    public void validateInputMoney(String money) {
        try{
            Integer.parseInt(money);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("허용되지 않는 입력입니다.", e);
        }
    }
}
