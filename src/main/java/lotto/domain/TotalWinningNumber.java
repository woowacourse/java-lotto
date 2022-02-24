package lotto.domain;

import java.util.Set;

public class TotalWinningNumber {

    private final WinningNumber winningNumber;
    private final LottoNumber bonusNumber;

    public TotalWinningNumber(WinningNumber winningNumber, LottoNumber bonusNumber) {
        validateDuplication(winningNumber, bonusNumber);
        this.winningNumber = winningNumber;
        this.bonusNumber = bonusNumber;
    }

    private void validateDuplication(WinningNumber winningNumber, LottoNumber bonusNumber) {
        if (winningNumber.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 볼은 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public Set<LottoNumber> getWinningAndBonusNumber() {
        Set<LottoNumber> totalNumbers = winningNumber.toSet();
        totalNumbers.add(bonusNumber);
        return totalNumbers;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }
}
