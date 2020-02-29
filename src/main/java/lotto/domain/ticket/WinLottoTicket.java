package lotto.domain.ticket;

import lotto.domain.result.LottoMatchResult;
import lotto.domain.result.rank.Rank;
import lotto.domain.ticket.ball.LottoBall;
import lotto.domain.ticket.ball.LottoBallFactory;
import lotto.util.NullOrEmptyValidator;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class WinLottoTicket {
    private static final String MESSAGE_FOR_OVERLAP_NUMBERS = "로또 당첨 번호와 보너스 번호는 중복될 수 없습니다.";

    private final LottoTicket winningTicket;
    private final LottoBall bonusBall;

    public WinLottoTicket(List<Integer> winNumbers, int bonusNumber) {
        NullOrEmptyValidator.isNullOrEmpty(winNumbers);
        validateBonusNumber(winNumbers, bonusNumber);

        Set<LottoBall> winBalls = winNumbers.stream()
                .map(LottoBallFactory::getLottoBallByNumber)
                .collect(Collectors.toSet());

        this.winningTicket = new LottoTicket(winBalls);
        this.bonusBall = LottoBallFactory.getLottoBallByNumber(bonusNumber);
    }

    private void validateBonusNumber(List<Integer> winNumbers, int bonusNumber) {
        if (winNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(MESSAGE_FOR_OVERLAP_NUMBERS);
        }
    }

    public LottoMatchResult createLottoMatchResult(LottoTicket lottoTicket) {
        NullOrEmptyValidator.isNull(lottoTicket);

        int numberOfMatch = countMatchNumber(lottoTicket);
        boolean isBonusMatch = lottoTicket.has(bonusBall);

        Rank rank = Rank.findRankByMatchInfo(numberOfMatch, isBonusMatch);
        return new LottoMatchResult(rank);
    }

    public int countMatchNumber(LottoTicket lottoTicket) {
        NullOrEmptyValidator.isNull(lottoTicket);

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
