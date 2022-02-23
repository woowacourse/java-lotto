package domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum WinnerPrice {

    FIRST(2000000000, 6),
    SECOND(30000000, -1),
    THIRD(1500000, 5),
    FOURTH(50000, 4),
    FIFTH(5000, 3),
    FAIL(0, 0);

    private final int prize;
    private final int matched;

    WinnerPrice(final int prize, final int matched) {
        this.prize = prize;
        this.matched = matched;
    }

    public static WinnerPrice getWinnerPriceByMatched(int matched) {
        return Arrays.stream(WinnerPrice.values())
                .filter(winnerPrice -> winnerPrice.matched == matched)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException());
    }

    public static List<WinnerPrice> getWinnerPrices() {
        return Arrays.stream(WinnerPrice.values())
                .sequential()
                .collect(Collectors.toList());
    }

    public int getPrize() {
        return prize;
    }

    public int getMatched() {
        return matched;
    }

}
