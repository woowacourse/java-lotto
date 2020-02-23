package lotto.domain.ticket;

import lotto.domain.result.LottoResult;
import lotto.domain.ticket.ball.LottoBall;

import java.util.Objects;

public class WinLottoTicket {
    private static final String MESSAGE_FOR_OVERLAP_NUMBERS = "로또 당첨 번호와 보너스 번호는 중복될 수 없습니다.";

    private final LottoTicket winningTicket;
    private final LottoBall bonusBall;

    public WinLottoTicket(LottoTicket winningTicket, LottoBall bonusBall) {
        validateBonusBall(winningTicket, bonusBall);
        this.winningTicket = winningTicket;
        this.bonusBall = bonusBall;
    }

    private void validateBonusBall(LottoTicket winningTicket, LottoBall bonusBall) {
        if (winningTicket.has(bonusBall)) {
            throw new IllegalArgumentException(MESSAGE_FOR_OVERLAP_NUMBERS);
        }
    }

    public LottoResult createLottoResult(LottoTicket lottoTicket) {
        int numberOfMatch = countMatchNumber(lottoTicket);
        boolean isBonusMatch = lottoTicket.has(bonusBall);

        return new LottoResult(numberOfMatch, isBonusMatch);
    }

    public int countMatchNumber(LottoTicket lottoTicket) {
        return (int) this.winningTicket.getLottoBalls().stream()
                .filter(lottoTicket::has)
                .count();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinLottoTicket that = (WinLottoTicket) o;
        return Objects.equals(winningTicket, that.winningTicket) &&
                Objects.equals(bonusBall, that.bonusBall);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winningTicket, bonusBall);
    }
}
