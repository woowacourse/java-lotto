package lotto.domain;

import java.util.List;

public class WinningLotto extends Lotto {
    public static final String DUPLICATE_BONUS_NUMBER_ERROR = "보너스 번호가 당첨 번호와 중복됩니다.";

    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto winningLotto, LottoNumber bonusNumber) {
        super(winningLotto.toList());

        validateDuplicatesWithLottoNumbers(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    private void validateDuplicatesWithLottoNumbers(LottoNumber bonusNumber) {
        if (bonusNumber.hasAnyMatchingNumber(lottoNumbers)) {
            throw new IllegalArgumentException(DUPLICATE_BONUS_NUMBER_ERROR);
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
