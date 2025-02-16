package domain;

import java.util.List;

public class BonusNumber {
    private final int number;

    public BonusNumber(String bonusNumberInput, WinningNumber winningNumber) {
        validate(bonusNumberInput, winningNumber);
        this.number = Integer.parseInt(bonusNumberInput);
    }

    public boolean matchesWith(List<Integer> lottoNumbers) {
        return lottoNumbers.contains(number);
    }

    public int getNumber() {
        return number;
    }

    private void validate(String bonusNumberInput, WinningNumber winningNumber) {
        try {
            int bonusNumber = Integer.parseInt(bonusNumberInput);
            throwRangeException(bonusNumber);
            throwDuplicateException(bonusNumber, winningNumber);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("보너스 볼은 정수로 입력해주세요.");
        }
    }

    private void throwRangeException(int bonusNumber) {
        if (bonusNumber < LottoConstants.MIN_NUMBER || bonusNumber > LottoConstants.MAX_NUMBER) {
            throw new IllegalArgumentException("보너스 볼은 1~45 사이의 정수로 입력해주세요.");
        }
    }

    private void throwDuplicateException(int bonusNumber, WinningNumber winningNumber) {
        if (winningNumber.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 볼은 당첨 번호와 중복되지 않게 입력해주세요.");
        }
    }
}
