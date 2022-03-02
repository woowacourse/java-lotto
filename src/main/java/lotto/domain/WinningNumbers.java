package lotto.domain;

import java.util.Objects;
import java.util.Optional;
import lotto.domain.lottoticket.LottoTicket;

public final class WinningNumbers {
    private static final String DUPLICATE_ERROR = "보너스 번호는 당첨 번호와 중복될 수 없습니다";

    private final LottoTicket lottoNumbers;
    private final LottoNumber bonusNumber;

    public WinningNumbers(LottoTicket lottoNumbers, LottoNumber bonusNumber) {
        validateDuplicateNumber(lottoNumbers, bonusNumber);
        this.lottoNumbers = lottoNumbers;
        this.bonusNumber = bonusNumber;
    }

    public Optional<Ranking> calculateRanking(LottoTicket otherLottoNumbers) {
        int hitCount = lottoNumbers.calculateSameCount(otherLottoNumbers);
        boolean hasBonusBall = otherLottoNumbers.isContains(bonusNumber);
        return Ranking.findRanking(hitCount, hasBonusBall);
    }

    private void validateDuplicateNumber(LottoTicket lottoNumbers, LottoNumber bonusNumber) {
        if (lottoNumbers.isContains(bonusNumber)) {
            throw new IllegalArgumentException(DUPLICATE_ERROR);
        }
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
        return Objects.equals(lottoNumbers, that.lottoNumbers) && Objects.equals(bonusNumber,
                that.bonusNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers, bonusNumber);
    }

    @Override
    public String toString() {
        return "WinningNumbers{" +
                "lottoNumbers=" + lottoNumbers +
                ", bonusNumber=" + bonusNumber +
                '}';
    }
}
