package lotto.model;

public class BonusBall {
    private int bonusNo;

    public BonusBall(String input) {
        int inputNo = isNumber(input);
        isContainsWinNumber(inputNo);
        this.bonusNo = inputNo;
    }

    private int isNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("숫자가 아닙니다.");
        }
    }

    private void isContainsWinNumber(int inputNo) {
        if (WinNumber.winNumbers.contains(inputNo)) {
            throw new IllegalArgumentException("당첨번호와 중복되는 숫자가 있습니다.");
        }
    }
}
