package domain;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public enum WinningInfo {

    FIRST_PRIZE(6, false, 2_000_000_000),
    SECOND_PRIZE(5, true, 30_000_000),
    THIRD_PRIZE(5, false, 1_500_000),
    FOURTH_PRIZE(4, false, 50_000),
    FIFTH_PRIZE(3, false, 5_000),
    NONE(0, false, 0);

    private final int matchedNumberCount;
    private final boolean isBonusMatched;
    private final int price;

    WinningInfo(int matchedNumberCount, boolean isBonusMatched, int price) {
        this.matchedNumberCount = matchedNumberCount;
        this.isBonusMatched = isBonusMatched;
        this.price = price;
    }

    public static WinningInfo of(int matchedNumberCount, boolean isBonusMatched) {
        return Arrays.stream(values())
                .filter(value -> value.getMatchedNumberCount() == matchedNumberCount
                        && value.isBonusMatched() == isBonusMatched)
                .findFirst()
                .orElse(NONE);
    }

    public static List<WinningInfo> getSortedValues() {
        return Arrays.stream(values())
                .filter(v -> v != WinningInfo.NONE)
                .sorted(Comparator.comparing(WinningInfo::getPrice))
                .toList();
    }

    public int getMatchedNumberCount() {
        return matchedNumberCount;
    }

    public boolean isBonusMatched() {
        return isBonusMatched;
    }

    public int getPrice() {
        return price;
    }
}
