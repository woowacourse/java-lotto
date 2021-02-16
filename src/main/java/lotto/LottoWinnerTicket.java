package lotto;

import java.util.List;
import java.util.Objects;

public class LottoWinnerTicket {
    private final List<LottoNumber> lottoWinnerNumbers;
    private final LottoWinnerBonusNumber lottoWinnerBonusNumber;

    public LottoWinnerTicket(List<LottoNumber> lottoWinnerNumbers, LottoWinnerBonusNumber lottoWinnerBonusNumber) {
        this.lottoWinnerNumbers = lottoWinnerNumbers;
        this.lottoWinnerBonusNumber = lottoWinnerBonusNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoWinnerTicket that = (LottoWinnerTicket) o;
        return Objects.equals(lottoWinnerNumbers, that.lottoWinnerNumbers) && Objects.equals(lottoWinnerBonusNumber, that.lottoWinnerBonusNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoWinnerNumbers, lottoWinnerBonusNumber);
    }
}
