package lotto.model;

public class BonusBall {
    public static int bonusNo;

    public BonusBall(String input) {
        int inputNumber = isNumber(input);
        isContainsWinNumber(inputNumber);
        this.bonusNo = inputNumber;
    }

    private int isNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("숫자가 아닙니다.");
        }
    }

    private void isContainsWinNumber(int inputNumber) {
        if (WinNumber.winNumbers.contains(inputNumber)) {
            throw new IllegalArgumentException("당첨번호와 중복되는 숫자가 있습니다.");
        }
    }
}
