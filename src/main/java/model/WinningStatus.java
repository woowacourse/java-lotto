package model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public enum WinningStatus {
    FIRST(2_000_000_000, 6, false),
    SECOND(30_000_000, 5, true),
    THIRD(1_500_000, 5, false),
    FOURTH(50_000, 4, false),
    FIFTH(5_000, 3, false),
    NONE(0, 0, false);

    private final int price;
    private final int matchingCount;
    private final boolean matchesBonusNumber;

    WinningStatus(int price, int matchingCount, boolean matchesBonusNumber){
        this.price = price;
        this.matchingCount = matchingCount;
        this.matchesBonusNumber = matchesBonusNumber;
    }

    public static List<WinningStatus> getSorted() {
        return Arrays.stream(WinningStatus.values())
                .sorted(Comparator.comparing(WinningStatus::getPrice))
                .toList();
    }

    public static WinningStatus findBy(int matchingCount, boolean matchesBonusNumber) {
        if (matchingCount == THIRD.matchingCount) {
            return decideByBonusNumber(matchesBonusNumber);
        }
        return decideByMatchingCount(matchingCount);
    }

    private static WinningStatus decideByMatchingCount(int matchingCount) {
        for (WinningStatus winningStatus : WinningStatus.values()) {
            if (winningStatus.matchingCount == matchingCount) {
                return winningStatus;
            }
        }
        return NONE;
    }

    private static WinningStatus decideByBonusNumber(boolean matchesBonusNumber) {
        if(matchesBonusNumber) {
            return SECOND;
        }
        return THIRD;
    }

    public int getPrice() {
        return price;
    }

    public int getMatchingCount() {
        return matchingCount;
    }

    public boolean matchesBonusNumber() {
        return matchesBonusNumber;
    }
}
