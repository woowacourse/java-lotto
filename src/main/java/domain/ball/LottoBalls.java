package domain.ball;

import domain.result.LottoRank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class LottoBalls {
    private static final int LOTTO_NUMBERS_SIZE = 6;

    private final List<LottoBall> lottoBalls;

    public LottoBalls(final List<LottoBall> lottoBalls) {
        List<LottoBall> copy = new ArrayList<>(lottoBalls);
        validateLottoNumbers(copy);
        this.lottoBalls = copy;
    }

    private void validateLottoNumbers(final List<LottoBall> lottoBalls) {
        validateDuplicate(lottoBalls);
        validateSize(lottoBalls);
    }

    private void validateDuplicate(final List<LottoBall> lottoBalls) {
        boolean isUnique = lottoBalls.stream()
                .allMatch(new HashSet<>()::add);
        if (!isUnique) {
            throw new IllegalArgumentException("로또 번호에 중복된 값이 있습니다. 다시 입력해주세요.");
        }
    }

    private void validateSize(final List<LottoBall> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException("6개의 로또 번호가 필요합니다.");
        }
    }

    public boolean containNumber(final LottoBall targetLottoBall) {
        return lottoBalls.stream()
                .anyMatch(targetLottoBall::equals);
    }

    public List<LottoBall> getLottoNumbers() {
        List<LottoBall> copy = new ArrayList<>(this.lottoBalls);
        return Collections.unmodifiableList(copy);
    }

    public LottoRank matchCount(LottoBalls lottoBalls, LottoBall bonusBall) {
        int count = (int) this.lottoBalls.stream()
                .filter(lottoBalls::contains)
                .count();

        boolean containBonus = this.lottoBalls.stream()
                .anyMatch(bonusBall::equals);

        return LottoRank.findByBonusWithMatches(containBonus, count);
    }

    private boolean contains(LottoBall lottoBall) {
        return this.lottoBalls.contains(lottoBall);
    }
}
