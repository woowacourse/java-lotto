package domain.ball;

import domain.result.LottoRank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class LottoBalls {
    public static final int LOTTO_BALL_SIZE = 6;
    private static final String DUPLICATE_EXCEPTION_MESSAGE = "로또 번호에 중복된 값이 있습니다. 다시 입력해주세요. 입력값 %s";
    private static final String LOTTO_SIZE_EXCEPTION_MESSAGE = "%d개의 로또 번호가 필요합니다.";

    private final List<LottoBall> lottoBalls;

    public LottoBalls(final List<LottoBall> lottoBalls) {
        List<LottoBall> copy = new ArrayList<>(lottoBalls);
        validateLottoNumbers(copy);
        this.lottoBalls = copy;
    }

    public List<LottoBall> getLottoBalls() {
        List<LottoBall> copy = new ArrayList<>(this.lottoBalls);
        return Collections.unmodifiableList(copy);
    }

    public LottoRank findRankByWinningBalls(final LottoBalls winningBalls, final LottoBall bonusBall) {
        int count = (int) this.lottoBalls.stream()
                .filter(winningBalls::contains)
                .count();

        boolean hasBonus = this.lottoBalls.stream()
                .anyMatch(bonusBall::equals);

        return LottoRank.findRankByBonusAndMatches(hasBonus, count);
    }

    private void validateLottoNumbers(final List<LottoBall> lottoBalls) {
        validateDuplicate(lottoBalls);
        validateSize(lottoBalls);
    }

    private void validateDuplicate(final List<LottoBall> lottoBalls) {
        boolean isUnique = lottoBalls.stream()
                .allMatch(new HashSet<>()::add);
        if (!isUnique) {
            throw new IllegalArgumentException(String.format(DUPLICATE_EXCEPTION_MESSAGE, lottoBalls.toString()));
        }
    }

    private void validateSize(final List<LottoBall> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_BALL_SIZE) {
            throw new IllegalArgumentException(String.format(LOTTO_SIZE_EXCEPTION_MESSAGE, LOTTO_BALL_SIZE));
        }
    }

    private boolean contains(final LottoBall lottoBall) {
        return this.lottoBalls.contains(lottoBall);
    }
}
