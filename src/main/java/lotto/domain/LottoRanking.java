package lotto.domain;

import java.util.Arrays;

public enum LottoRanking {

    FAIL(0, false, 0),
    FIFTH(3, false, 5_000),
    FOURTH(4, false, 50_000),
    THIRD(5, false, 1_500_000) {
        @Override
        public boolean match(int count, boolean containsBonus) {
            return super.match(count, containsBonus) && !containsBonus;
        }
    },
    SECOND(5, true, 30_000_000) {
        @Override
        public boolean match(int count, boolean containsBonus) {
            return super.match(count, containsBonus) && containsBonus;
        }
    },
    FIRST(6, false, 2_000_000_000);

    private final int count;
    private final boolean containsBonus;
    private final int price;

    LottoRanking(int count, boolean containsBonus, int price) {
        this.count = count;
        this.containsBonus = containsBonus;
        this.price = price;
    }

    public static LottoRanking of(int count, boolean containsBonus) {
        return Arrays.stream(values())
            .filter(ranking -> ranking.match(count, containsBonus))
            .findAny()
            .orElse(LottoRanking.FAIL);
    }

    public boolean match(int count, boolean containsBonus) {
        return this.count == count;
    }

    public long getPrice() {
        return price;
    }

    public long multiply(int count) {
        return (long) price * count;
    }

    public int getCount() {
        return count;
    }

    public boolean isContainsBonus() {
        return containsBonus;
    }
}
