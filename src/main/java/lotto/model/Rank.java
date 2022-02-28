package lotto.model;

import java.util.Arrays;

public enum Rank {
    LOSER(0, false, 0),
    FIFTH(3, false, 5_000),
    FOURTH(4, false, 50_000),
    THIRD(5, false, 1_500_000),
    SECOND(5, true, 30_000_000),
    FIRST(6, false, 2_000_000_000);

    private final int count;
    private final boolean winBonusNumber;
    private final int price;

    Rank(final int count, final boolean winBonusNumber, final int price) {
        this.count = count;
        this.winBonusNumber = winBonusNumber;
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public boolean isWinBonusNumber() {
        return winBonusNumber;
    }

    public int getPrice() {
        return price;
    }

    public static Rank getRank(final int count, final boolean winBonusNumber) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.count == count)
                .filter(rank -> rank.winBonusNumber == winBonusNumber)
                .findFirst()
                .orElse(LOSER);
    }
}
