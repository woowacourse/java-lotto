package lotto.model.prize;

import java.util.Arrays;
import java.util.function.Predicate;

public enum Prize {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    NONE(0, false, 0);

    private final int matchCount;
    private final boolean bonus;
    private final long amount;
    private Predicate<MatchResult> winningCondition;

    Prize(int matchCount, boolean bonus, long amount) {
        this.matchCount = matchCount;
        this.bonus = bonus;
        this.amount = amount;
        instanceWinningCondition(matchCount, bonus);
    }

    private void instanceWinningCondition(int matchCount, boolean bonus) {
        if (bonus) {
            this.winningCondition =
                    (matchResult) -> matchResult.isCount(matchCount) && matchResult.isBonus();
            return;
        }
        this.winningCondition = (matchResult) -> matchResult.isCount(matchCount);
    }

    public static Prize getPrize(MatchResult matchResult) {
        return Arrays.stream(values())
                .filter(prize -> prize.winningCondition.test(matchResult))
                .findFirst()
                .orElse(NONE);

    }

    public long pickAmount(Long count) {
        return this.amount * count;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public long getAmount() {
        return amount;
    }

    public boolean isBonus() {
        return bonus;
    }
}
