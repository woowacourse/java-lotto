package lotto.domain.result.win;

import lotto.domain.result.MatchResult;
import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.ball.LottoNumber;
import lotto.domain.ticket.ball.LottoNumberFactory;

import java.util.Objects;
import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static lotto.domain.ticket.ball.LottoNumberFactory.findLottoBallByNumber;

public class WinningLotto {
    private final WinningBalls winningBalls;
    private final LottoNumber bonusBall;

    public WinningLotto(Set<Integer> winningNumbers, int bonusNumber) {
        validateWinningNumbers(winningNumbers);
        validateBonusNumber(winningNumbers, bonusNumber);
        this.bonusBall = findLottoBallByNumber(bonusNumber);
        this.winningBalls = makeWinningBalls(winningNumbers, bonusBall);
    }

    private void validateWinningNumbers(Set<Integer> winningNumbers) {
        if (winningNumbers.size() != 6) {
            throw new IllegalArgumentException(String.format("입력받은 갯수 %d : 우승 번호는 6개입니다.", winningNumbers.size()));
        }
    }

    private void validateBonusNumber(Set<Integer> winningNumbers, int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(String.format("보너스 번호 %d는 중복된 번호입니다.", bonusNumber));
        }
    }

    private WinningBalls makeWinningBalls(Set<Integer> winningNumber, LottoNumber bonusBall) {
        Set<LottoNumber> collectBalls = winningNumber.stream()
                .map(LottoNumberFactory::findLottoBallByNumber)
                .collect(toSet());
        return new WinningBalls(collectBalls, bonusBall);
    }

    public MatchResult getResult(LottoTicket buyLottoTicket) {
        int matchCount = winningBalls.getMatchCount(buyLottoTicket);
        boolean isBonusMatch = buyLottoTicket.has(bonusBall);

        return new MatchResult(matchCount, isBonusMatch);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinningLotto that = (WinningLotto) o;
        return Objects.equals(winningBalls, that.winningBalls) &&
                Objects.equals(bonusBall, that.bonusBall);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winningBalls, bonusBall);
    }
}
