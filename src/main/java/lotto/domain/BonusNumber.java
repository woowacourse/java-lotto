package lotto.domain;

public class BonusNumber {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    private int bonus;

    public BonusNumber(String bonusNumberInput) {
        this.bonus = getValidatedBonusNumber(bonusNumberInput);
    }

    private int getValidatedBonusNumber(String bonusNumberInput) {
        checkNoInput(bonusNumberInput);
        checkType(bonusNumberInput);
        checkRange(bonusNumberInput);
        return Integer.parseInt(bonusNumberInput);
    }

    private void checkNoInput(String bonusNumberInput) {
        if (bonusNumberInput == null || bonusNumberInput.trim().isEmpty()) {
            throw new RuntimeException("보너스 번호를 입력하지 않으셨습니다.");
        }
    }

    private void checkType(String bonusNumberInput) {
        try {
            Integer.parseInt(bonusNumberInput);
        } catch (NumberFormatException e) {
            throw new RuntimeException("숫자만 입력하시기 바랍니다.");
        }
    }

    private void checkRange(String bonusNumberInput) {
        int bonus = Integer.parseInt(bonusNumberInput);
        if (bonus < MIN_NUMBER || MAX_NUMBER < bonus) {
            throw new RuntimeException(String.format("%d 이상 %d 이하의 숫자만 입력 가능합니다.", MIN_NUMBER, MAX_NUMBER));
        }
    }
}
