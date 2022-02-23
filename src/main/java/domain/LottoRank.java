package domain;

public enum LottoRank {

    RANK_5(3, 5000),
    RANK_4(4, 50000),
    RANK_3(5, 1500000),
    RANK_2(5, 30000000),
    RANK_1(6, 2000000000),
    RANK_NOTHING(0, 0);

    private final int count;
    private final int price;

    LottoRank(int count, int price) {
        this.count = count;
        this.price = price;
    }

    public static LottoRank getRankByCountAndBonus(int count, boolean bonus) {
        for (LottoRank rank : LottoRank.values()) {
            if (rank.count == count) {
                if (count == 5) {
                    if (bonus) {
                        return RANK_2;
                    }
                    return RANK_3;
                }
                return rank;
            }
        }
        return RANK_NOTHING;
    }

    public int getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }
}
