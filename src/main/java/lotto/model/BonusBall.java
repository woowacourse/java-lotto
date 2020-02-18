package lotto.model;

public class BonusBall {
    private int bonusNo;

    public BonusBall(String input) {
        isNumber(input);
    }

    private void isNumber(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("숫자가 아닙니다.");
        }
    }
}
