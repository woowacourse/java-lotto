package domain;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public enum LottoPrize {

    FIRST(6, BonusMatch.NOT_MATCH, 2_000_000_000),
    SECOND(5, BonusMatch.MATCH, 30_000_000),
    THIRD(5, BonusMatch.NONE, 1_500_000),
    FOURTH(4, BonusMatch.NONE, 50_000),
    FIFTH(3, BonusMatch.NONE, 5_000),
    NONE(0, BonusMatch.NONE, 0)
    ;

    private final int matchedNumberCount;
    private final BonusMatch bonusMatch;
    private final int price;

    LottoPrize(int matchedNumberCount, BonusMatch bonusMatch, int price) {
        this.matchedNumberCount = matchedNumberCount;
        this.bonusMatch = bonusMatch;
        this.price = price;
    }

    public static LottoPrize from(int matchedNumberCount, boolean isBonusMatched) {
        return Arrays.stream(values())
                .filter(value -> value.getMatchedNumberCount() == matchedNumberCount
                        && value.getBonusMatch().isMatch(isBonusMatched))
                .findFirst()
                .orElse(NONE);
    }

    public static List<LottoPrize> getSortedValues() {
        return Arrays.stream(values())
                .filter(v -> v != LottoPrize.NONE)
                .sorted(Comparator.comparing(LottoPrize::getPrice))
                .toList();
    }

    public int getMatchedNumberCount() {
        return matchedNumberCount;
    }

    public BonusMatch getBonusMatch() {
        return bonusMatch;
    }

    public int getPrice() {
        return price;
    }
}
