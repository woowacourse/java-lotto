package lotto.domain;

import java.util.List;

public class WinningLotto extends Lotto {
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto winningLotto, LottoNumber bonusNumber) {
        super(winningLotto.toList());

        validateDuplicatesWithLottoNumbers(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    private void validateDuplicatesWithLottoNumbers(LottoNumber bonusNumber) {
        if (bonusNumber.hasAnyMatchingNumber(lottoNumbers)) {
            throw new IllegalArgumentException();
        }
    }

    public int countMatchingNumbers(List<LottoNumber> lottoNumbers) {
        return (int) this.lottoNumbers
                .stream()
                .filter(lottoNumbers::contains)
                .count();
    }

    public boolean hasBonusMatch(List<LottoNumber> numbers) {
        return numbers.contains(bonusNumber);
    }
}
