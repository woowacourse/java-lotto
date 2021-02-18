package domain.result;

import domain.budget.Budget;

import java.util.Arrays;

public enum LottoRank {
    NONE_MATCHES(0, Budget.amounts(0), false),
    THREE_MATCHES(3, Budget.amounts(5_000), false),
    FOUR_MATCHES(4, Budget.amounts(50_000), false),
    FIVE_MATCHES(5, Budget.amounts(1_500_000), false),
    FIVE_AND_BONUS_MATCHES(5, Budget.amounts(30_000_000), true),
    SIX_MATCHES(6, Budget.amounts(2_000_000_000), false);

    private static final String STRING_FORMATTER = "%s개 일치 (%d원)";

    private final int matches;
    private final Budget budget;
    private final boolean isBonus;

    LottoRank(int matches, Budget budget, boolean isBonus) {
        this.matches = matches;
        this.budget = budget;
        this.isBonus = isBonus;
    }

    public static LottoRank findByBonusWithMatches(boolean containBonus, int matches) {
        if (!containBonus || matches != 5) {
            return Arrays.stream(values()).filter(lottoRank -> lottoRank.isSameMatches(matches))
                    .findFirst().orElse(NONE_MATCHES);
        }
        return FIVE_AND_BONUS_MATCHES;
    }

    public int getMatches() {
        return matches;
    }

    private boolean isSameMatches(int matches) {
        return this.matches == matches;
    }

    private boolean checkBonus(boolean isBonus) {
        return this.isBonus == isBonus;
    }

    public String makeSentence() {
        return String.format(STRING_FORMATTER, matches, budget.getIntValue());
    }
}
