package lotto.domain.lotto;

import java.util.Objects;
import lotto.domain.number.LottoNumber;
import lotto.domain.rank.Rank;

public class WinningNumbers {

    private final LottoNumbers lottoNumbers;
    private final LottoNumber bonusNumber;

    public WinningNumbers(LottoNumbers lottoNumbers, LottoNumber bonusNumber) {
        validateDuplication(lottoNumbers, bonusNumber);
        this.lottoNumbers = lottoNumbers;
        this.bonusNumber = bonusNumber;
    }

    private static void validateDuplication(LottoNumbers lottoNumbers, LottoNumber bonusNumber) {
        if (lottoNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 로또 번호와 달라야 합니다.");
        }
    }

    public Rank checkRank(LottoNumbers lottoNumbers) {
        return Rank.valueOf(
            getMatchCount(lottoNumbers),
            hasBonusNumber(lottoNumbers)
        );
    }

    private boolean hasBonusNumber(LottoNumbers lottoNumbers) {
        return lottoNumbers.contains(bonusNumber);
    }

    private int getMatchCount(LottoNumbers lottoNumbers) {
        return this.lottoNumbers.match(lottoNumbers);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WinningNumbers that = (WinningNumbers) o;
        return Objects.equals(lottoNumbers, that.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }
}