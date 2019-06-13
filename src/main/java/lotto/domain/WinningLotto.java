package lotto.domain;

import java.util.Objects;

public class WinningLotto {
    private final LottoTicket winningLottoTicket;
    private final LottoNumber bonusBall;

    public WinningLotto(LottoTicket winningLottoTicket, int bonusBall) {
        this.winningLottoTicket = winningLottoTicket;
        this.bonusBall = LottoNumber.valueOf(bonusBall);
        validateWinningLotto();
    }

    private void validateWinningLotto() {
        if (winningLottoTicket.hasBonusBall(bonusBall)) {
            throw new IllegalArgumentException("로또 번호와 보너스 번호는 중복 안됩니다.");
        }
    }

    public LottoRank getRank(LottoTicket lottoTicket) {
        int countOfMatch = winningLottoTicket.getSameCount(lottoTicket);
        boolean hasBonusBall = lottoTicket.hasBonusBall(bonusBall);
        return LottoRank.valueOf(countOfMatch,hasBonusBall);
    }

    public LottoTicket getWinningLottoTicket() {
        return winningLottoTicket;
    }

    public LottoNumber getBonusBall() {
        return bonusBall;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinningLotto that = (WinningLotto) o;
        return Objects.equals(winningLottoTicket, that.winningLottoTicket) &&
                Objects.equals(bonusBall, that.bonusBall);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winningLottoTicket, bonusBall);
    }
}
