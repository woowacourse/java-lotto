package model;

import java.util.List;

public class BonusNumber {

    private final int number;

    public BonusNumber(int number, WinningNumber winningNumber) {
        validate(number, winningNumber);
        this.number = number;
    }

    public boolean matchesWith(List<Integer> lottoNumbers) {
        return lottoNumbers.contains(number);
    }

    public int getNumber() {
        return number;
    }

    private void validate(int number, WinningNumber winningNumber) {
        validateBonusNumberRange(number);
        validateBonusNumberDuplication(winningNumber, number);
    }

    private void validateBonusNumberRange(int number) {
        if (number < LottoConstants.MIN_NUMBER || number > LottoConstants.MAX_NUMBER) {
            throw new IllegalArgumentException("보너스 볼은 1~45 사이의 정수로 입력해주세요.");
        }
    }

    private void validateBonusNumberDuplication(WinningNumber winningNumber, int number) {
        if (winningNumber.contains(number)) {
            throw new IllegalArgumentException("보너스 볼은 당첨 번호와 중복되지 않게 입력해주세요.");
        }
    }
}
