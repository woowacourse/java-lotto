package domain.ball;

import domain.result.LottoRank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class LottoBalls {
    private static final int LOTTO_NUMBERS_SIZE = 6;
    private static final int SPLIT_THRESHOLD = -1;
    private static final String DELIMITER = ", ";

    private final List<LottoBall> lottoBalls;

    private LottoBalls(final List<LottoBall> lottoBalls) {
        List<LottoBall> copy = new ArrayList<>(lottoBalls);
        validateLottoNumbers(copy);
        this.lottoBalls = copy;
    }

    public LottoBalls(final String lottoNumbers) {
        String[] temp = lottoNumbers.split(DELIMITER, SPLIT_THRESHOLD);
        List<LottoBall> tempLottoBalls = new ArrayList<>();
        for (int i = 0; i < temp.length; i++) {
            tempLottoBalls.add(LottoBall.of(temp[i]));
        }
        validateLottoNumbers(tempLottoBalls);
        this.lottoBalls = tempLottoBalls;
    }

    public static LottoBalls of(final List<LottoBall> lottoBalls) {
        return new LottoBalls(lottoBalls);
    }

    public static LottoBalls generate(final List<Integer> lottoNumbers) {
        List<LottoBall> lottoBallGroup = lottoNumbers.stream()
                .map(lottoNumber -> new LottoBall(lottoNumber))
                .collect(Collectors.toList());
        return new LottoBalls(lottoBallGroup);
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

    private void validateSize(final List lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException("6개의 로또 번호가 필요합니다.");
        }
    }

    public boolean containNumber(final BonusBall bonusBall) {
        return lottoBalls.stream()
                .anyMatch(lottoNumber -> bonusBall.isSameNumber(lottoNumber));
    }

    public List<LottoBall> getLottoNumbers() {
        List<LottoBall> copy = new ArrayList<>(this.lottoBalls);
        return Collections.unmodifiableList(copy);
    }

    public LottoRank matchCount(LottoBalls lottoBalls, BonusBall bonusBall) {
        int count = (int) this.lottoBalls.stream()
                .filter(lottoNumber -> lottoBalls.contains(lottoNumber))
                .count();

        boolean containBonus = this.lottoBalls.stream()
                .anyMatch(lottoNumber -> bonusBall.isSameNumber(lottoNumber));

        return LottoRank.findByBonusWithMatches(containBonus, count);
    }

    private boolean contains(LottoBall lottoBall) {
        return this.lottoBalls.contains(lottoBall);
    }
}
