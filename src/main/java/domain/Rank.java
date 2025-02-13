package domain;

import java.util.Arrays;

public enum Rank {
    MISS(0, false, 0, ""),
    RANK5(3, false, 5000, "3개 일치 (5000원)- "),
    RANK4(4, false, 50000, "4개 일치 (50000원)- "),
    RANK3(5, false, 1500000, "5개 일치 (1500000원)- "),
    RANK2(5, true, 30000000, "5개 일치, 보너스 볼 일치(30000000원)- "),
    RANK1(6, false, 2000000000, "6개 일치 (2000000000원)- ");

    private int rank;
    private boolean bonus;
    private int price;
    private String msg;

    Rank(int rank, boolean bonus, int price, String msg) {
        this.rank = rank;
        this.bonus = bonus;
        this.price = price;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public int getPrice() {
        return price;
    }

    public boolean match(int match, boolean bonus) {
        if (this == RANK2 || this == RANK3) {
            return this.rank == match && this.bonus == bonus;
        }

        return this.rank == match;
    }

    public static Rank judge(int matchRank, boolean bonus) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.match(matchRank, bonus))
                .findFirst()
                .orElse(MISS);
    }
}
