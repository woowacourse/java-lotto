package model;

import java.util.List;

public class BonusNumber {

    private final int number;

    public BonusNumber(int bonusNumber, WinningNumber winningNumber) {
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException("보너스 볼은 1~45 사이의 정수로 입력해주세요.");
        }
        if (winningNumber.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 볼은 당첨 번호와 중복되지 않게 입력해주세요.");
        }
        this.number = bonusNumber;
    }

    public boolean matchesWith(List<Integer> lottoNumbers) {
        return lottoNumbers.contains(number);
    }
}
