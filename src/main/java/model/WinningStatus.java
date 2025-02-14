package model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public enum WinningStatus {
    FIRST(2_000_000_000, 6, false, "6개 일치 (2000000000원)"),
    SECOND(30_000_000, 5, true, "5개 일치, 보너스 볼 일치(30000000원)"),
    THIRD(1_500_000, 5, false, "5개 일치 (1500000원)"),
    FOURTH(50_000, 4, false, "4개 일치 (50000원)"),
    FIFTH(5_000, 3, false, "3개 일치 (5000원)"),
    NONE(0, 0, false, "");

    private final int price;
    private final int matchingCount;
    private final boolean matchesBonusNumber;
    private final String expression;

    WinningStatus(int price, int matchingCount, boolean matchesBonusNumber, String expression){
        this.price = price;
        this.matchingCount = matchingCount;
        this.matchesBonusNumber = matchesBonusNumber;
        this.expression = expression;
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

    public String getExpression() {
        return expression;
    }

    public int getPrice() {
        return price;
    }
}
