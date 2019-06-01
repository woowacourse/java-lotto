package lotto.domain;

import java.util.List;
import java.util.Objects;

public class LottoTicket {
    private final List<LottoNumber> lottoNumbers;

    public LottoTicket(final List<LottoNumber> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public int getMatchingCount(WinningLotto winningLotto) {
        int count = 0;

        for (LottoNumber lottoNumber : lottoNumbers) {
            count += winningLotto.hasEqualNumber(lottoNumber) ? 1 : 0;
        }

        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoTicket that = (LottoTicket) o;
        return Objects.equals(lottoNumbers, that.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }
}
