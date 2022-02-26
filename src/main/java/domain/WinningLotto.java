package domain;

import java.util.List;

public class WinningLotto {

    private static final String ERROR_DUPLICATE_BONUS = "보너스 번호가 당첨 번호와 중복됩니다.";

    private Lotto lotto;
    private LottoBall bonusBall;

    public WinningLotto(List<Integer> numbers, int bonus) {
        this.lotto = new Lotto(numbers);
        this.bonusBall = new LottoBall(bonus);
        if (lotto.hasBall(bonusBall)) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_BONUS);
        }
    }

    public Prize calculateRank(Lotto lotto) {
        int matched = lotto.matchedEachOther(this.lotto);
        boolean hasBonus = lotto.hasBall(bonusBall);
        return Prize.getWinnerPrizeByMatched(matched, hasBonus);
    }

}
