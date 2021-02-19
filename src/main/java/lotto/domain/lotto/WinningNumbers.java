package lotto.domain.lotto;

import java.util.Objects;
import lotto.domain.number.LottoNumber;

public class WinningNumbers {

    private final LottoNumbers lottoNumbers;
    private final LottoNumber bonusNumber;

    private WinningNumbers(LottoNumbers lottoNumbers, LottoNumber bonusNumber) {
        this.lottoNumbers = lottoNumbers;
        this.bonusNumber = bonusNumber;
    }

    public static WinningNumbers valueOf(String unparsedLottoNumbers, String unparsedBonusNumber) {
        LottoNumbers parsedLottoNumbers = LottoNumbers.valueOf(unparsedLottoNumbers);
        LottoNumber parsedBonusNumber = LottoNumber.valueOf(unparsedBonusNumber);

        validateDuplication(parsedLottoNumbers, parsedBonusNumber);

        return new WinningNumbers(parsedLottoNumbers, parsedBonusNumber);
    }

    private static void validateDuplication(LottoNumbers lottoNumbers, LottoNumber bonusNumber) {
        if (lottoNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 로또 번호와 달라야 합니다.");
        }
    }

    public boolean hasBonusNumber(LottoNumbers lottoNumbers) {
        return lottoNumbers.contains(bonusNumber);
    }

    public int getMatchCount(LottoNumbers lottoNumbers) {
        return this.lottoNumbers.getMatchCount(lottoNumbers);
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