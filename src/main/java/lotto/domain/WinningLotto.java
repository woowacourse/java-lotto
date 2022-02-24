package lotto.domain;

import java.util.Set;

public class WinningLotto {

    private final Lotto winningNumbers;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto winningNumbers, LottoNumber bonusNumber) {
        validateDuplication(winningNumbers, bonusNumber);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validateDuplication(Lotto winningNumber, LottoNumber bonusNumber) {
        if (winningNumber.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 볼은 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public Set<LottoNumber> getWinningAndBonusNumber() {
        Set<LottoNumber> totalNumbers = winningNumbers.getLottoNumbers();
        totalNumbers.add(bonusNumber);
        return totalNumbers;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }
}
