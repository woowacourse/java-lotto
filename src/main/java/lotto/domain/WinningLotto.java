package lotto.domain;

import java.util.List;
import java.util.Set;

public class WinningLotto {

    private final Lotto winningNumbers;
    private final LottoNumber bonusNumber;

    public WinningLotto(final List<Integer> winningNumbers, final int bonusNumber) {
        final LottoMachine lottoMachine = new FixedLottoMachine(winningNumbers);
        this.winningNumbers = new Lotto(lottoMachine);
        this.bonusNumber = new LottoNumber(bonusNumber);

        validateDuplication(this.winningNumbers, this.bonusNumber);
    }

    private void validateDuplication(final Lotto winningNumber, final LottoNumber bonusNumber) {
        if (winningNumber.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 볼은 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public Set<LottoNumber> getWinningAndBonusNumber() {
        final Set<LottoNumber> totalNumbers = winningNumbers.getLottoNumbers();
        totalNumbers.add(bonusNumber);
        return totalNumbers;
    }

    public LottoNumber getBonusNumber() {
        return new LottoNumber(bonusNumber.getNumber());
    }
}
