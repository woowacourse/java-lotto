package domain.ball;

import domain.result.LottoRank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static domain.ball.LottoBall.MAX_LOTTO_VALUE;
import static domain.ball.LottoBall.MIN_LOTTO_VALUE;

public class LottoBalls {
    private static final int LOTTO_BALL_SIZE = 6;

    private final List<LottoBall> lottoBalls;

    public LottoBalls(final List<LottoBall> lottoBalls) {
        List<LottoBall> copy = new ArrayList<>(lottoBalls);
        validateLottoNumbers(copy);
        this.lottoBalls = copy;
    }

    public static List<LottoBall> getRandomLottoBalls() {
        List<LottoBall> lottoBalls = IntStream.rangeClosed(MIN_LOTTO_VALUE, MAX_LOTTO_VALUE)
                .mapToObj(LottoBall::new)
                .collect(Collectors.toList());
        Collections.shuffle(lottoBalls);
        return lottoBalls.stream()
                .limit(LOTTO_BALL_SIZE)
                .sorted()
                .collect(Collectors.toList());
    }

    private void validateLottoNumbers(final List<LottoBall> lottoBalls) {
        validateDuplicate(lottoBalls);
        validateSize(lottoBalls);
    }

    private void validateDuplicate(final List<LottoBall> lottoBalls) {
        boolean isUnique = lottoBalls.stream()
                .allMatch(new HashSet<>()::add);
        if (!isUnique) {
            throw new IllegalArgumentException(String.format("로또 번호에 중복된 값이 있습니다. 다시 입력해주세요. 입력값 %s", lottoBalls.toString()));
        }
    }

    private void validateSize(final List<LottoBall> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_BALL_SIZE) {
            throw new IllegalArgumentException(String.format("%d개의 로또 번호가 필요합니다.", LOTTO_BALL_SIZE));
        }
    }

    public List<LottoBall> getLottoBalls() {
        List<LottoBall> copy = new ArrayList<>(this.lottoBalls);
        return Collections.unmodifiableList(copy);
    }

    public LottoRank matchCount(LottoBalls lottoBalls, LottoBall bonusBall) {
        int count = (int) this.lottoBalls.stream()
                .filter(lottoBalls::contains)
                .count();

        boolean containBonus = this.lottoBalls.stream()
                .anyMatch(bonusBall::equals);

        return LottoRank.findRankByBonusAndMatches(containBonus, count);
    }

    private boolean contains(LottoBall lottoBall) {
        return this.lottoBalls.contains(lottoBall);
    }
}
