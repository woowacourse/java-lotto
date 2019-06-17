package lotto.domain.lottoresult;

import lotto.domain.lottonumber.LottoNumber;
import lotto.domain.lottoticket.LottoTicket;

import java.util.List;
import java.util.Objects;

public class WinningLotto {
    private final LottoTicket winningTicket;
    private final LottoNumber bonusBall;

    private WinningLotto(LottoTicket winningTicket, LottoNumber bonusBall) {
        this.winningTicket = winningTicket;
        this.bonusBall = bonusBall;
    }

    public static WinningLotto of(LottoTicket winningTicket, LottoNumber bonusBall) {
        return new WinningLotto(winningTicket, bonusBall);
    }

    public boolean matchWinningTicket(LottoNumber lottoNumber) {
        return winningTicket.match(lottoNumber);
    }

    public boolean isBonusBallIn(List<LottoNumber> lottoNumbers) {
        return lottoNumbers.contains(bonusBall);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final WinningLotto that = (WinningLotto) o;
        return Objects.equals(winningTicket, that.winningTicket) &&
                Objects.equals(bonusBall, that.bonusBall);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winningTicket, bonusBall);
    }
}
