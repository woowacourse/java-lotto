package lotto.domain.result.win;

import lotto.domain.result.MatchResult;
import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.ball.LottoBall;
import lotto.domain.ticket.ball.LottoBallFactory;

import java.util.Objects;
import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static lotto.domain.ticket.ball.LottoBallFactory.findLottoBallByNumber;

public class WinningLotto {
    private static final String WINNING_NUMBER_SIZE_EXCEPTION_MESSAGE = "입력받은 갯수 %d : 우승 번호는 6개입니다.";
    private static final String DUPLICATE_BONUS_NUMBER_EXCEPTION_MESSAGE = "보너스 번호 %d는 중복된 번호입니다.";

    private final WinningBalls winningBalls;
    private final LottoBall bonusBall;

    public WinningLotto(Set<Integer> winningNumbers, int bonusNumber) {
        validateWinningNumbers(winningNumbers);
        validateBonusNumber(winningNumbers, bonusNumber);
        this.bonusBall = findLottoBallByNumber(bonusNumber);
        this.winningBalls = makeWinningBalls(winningNumbers, bonusBall);
    }

    private void validateWinningNumbers(Set<Integer> winningNumbers) {
        if (winningNumbers.size() != 6) {
            throw new IllegalArgumentException(String.format(WINNING_NUMBER_SIZE_EXCEPTION_MESSAGE, winningNumbers.size()));
        }
    }

    private void validateBonusNumber(Set<Integer> winningNumbers, int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(String.format(DUPLICATE_BONUS_NUMBER_EXCEPTION_MESSAGE, bonusNumber));
        }
    }

    private WinningBalls makeWinningBalls(Set<Integer> winningNumber, LottoBall bonusBall) {
        Set<LottoBall> collectBalls = winningNumber.stream()
                .map(LottoBallFactory::findLottoBallByNumber)
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
