package domain.lotto;

import domain.ball.LottoBall;
import domain.ball.LottoBalls;
import domain.result.LottoRank;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class WinningLotto {
    private static final String WINNING_NUMBERS_SIZE_EXCEPTION_MESSAGE = "우승 로또 번호의 갯수는 %d개 이어야 합니다. 현재 갯수: %d";
    private static final String DUPLICATE_BONUS_NUMBER_EXCEPTION_MESSAGE = "보너스 번호 %d는 중복된 번호입니다";
    private static final int WINNING_NUMBERS_SIZE = 6;

    private final LottoBalls winningBalls;
    private final LottoBall bonusBall;

    public WinningLotto(final Set<Integer> winningNumbers, final int bonusNumber) {
        validateWinningNumbers(winningNumbers);
        validateBonusNumber(winningNumbers, bonusNumber);
        this.winningBalls = makeWinningBalls(winningNumbers);
        this.bonusBall = makeBonusBall(bonusNumber);
    }

    public LottoRank findLottoRank(final LottoBalls lottoBalls) {
        return lottoBalls.findRankByWinningBalls(this.winningBalls, bonusBall);
    }

    private LottoBalls makeWinningBalls(final Set<Integer> winningNumbers) {
        List<LottoBall> lottoBalls = winningNumbers.stream()
                .map(LottoBall::new)
                .collect(Collectors.toList());
        return new LottoBalls(lottoBalls);
    }

    private LottoBall makeBonusBall(int bonusNumber) {
        return new LottoBall(bonusNumber);
    }

    private void validateWinningNumbers(Set<Integer> winningNumbers) {
        if (winningNumbers.size() != WINNING_NUMBERS_SIZE) {
            throw new IllegalArgumentException(String.format(WINNING_NUMBERS_SIZE_EXCEPTION_MESSAGE, WINNING_NUMBERS_SIZE, winningNumbers.size()));
        }
    }

    private void validateBonusNumber(final Set<Integer> winningNumbers, final int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(String.format(DUPLICATE_BONUS_NUMBER_EXCEPTION_MESSAGE, bonusNumber));
        }
    }
}
