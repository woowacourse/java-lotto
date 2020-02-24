package lotto.domain.result.win;

import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.ball.LottoNumber;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class WinningBalls {
    private final Set<LottoNumber> lottoNumbers;

    public WinningBalls(Set<LottoNumber> lottoNumbers, LottoNumber bonusBall) {
        this.lottoNumbers = new HashSet<>(lottoNumbers);
        this.lottoNumbers.add(bonusBall);
    }

    public int getMatchCount(LottoTicket lottoTicket) {
        return (int) this.lottoNumbers.stream()
                .filter(lottoTicket::has)
                .count();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinningBalls that = (WinningBalls) o;
        return Objects.equals(lottoNumbers, that.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }
}
