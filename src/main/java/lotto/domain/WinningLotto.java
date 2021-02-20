package lotto.domain;

import java.util.Collections;
import java.util.List;

public class WinningLotto extends Lotto {
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto winningLotto, LottoNumber bonusNumber) {
        super(winningLotto.toList());

        validateDuplicatesWithLottoNumbers(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    private void validateDuplicatesWithLottoNumbers(LottoNumber number) {
        if (number.hasAnyMatchingNumber(lottoNumbers)) {
            throw new IllegalArgumentException();
        }
    }

    public List<LottoNumber> getWinningLottoNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }

    public int getBonusNumberAsInt() {
        return bonusNumber.getNumber();
    }
}
