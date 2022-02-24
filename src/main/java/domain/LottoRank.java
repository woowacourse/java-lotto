package domain;

import java.util.Arrays;

public enum LottoRank {

    RANK_5(3, false, 5000),
    RANK_4(4, false, 50000),
    RANK_3(5, false, 1500000),
    RANK_2(5, true, 30000000),
    RANK_1(6, false, 2000000000),
    RANK_NOTHING(0, false,0);

    private static final int SECOND_AND_THIRD_RANK_COUNT = 5;

    private final int count;
    private final boolean bonus;
    private final int price;

    LottoRank(int count, boolean bonus, int price) {
        this.count = count;
        this.bonus = bonus;
        this.price = price;
    }

    public static LottoRank getRankByCountAndBonus(int count, boolean bonus) {
        return Arrays.stream(values())
                .filter(lottoRank -> lottoRank.matchRankAndBonus(count, bonus))
                .findFirst()
                .orElse(RANK_NOTHING);
    }

    public int getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }

    private boolean matchRankAndBonus(int count, boolean bonus) {
        return this.count == count && this.bonus == bonus;
    }
}
