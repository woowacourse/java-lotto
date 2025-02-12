package model;

import java.util.Arrays;
import java.util.Optional;

public enum Prize {
    _1ST(1, 6, false, 2_000_000_000),
    _2ND(2, 5, true, 30_000_000),
    _3RD(3, 5, false, 1_500_000),
    _4TH(4, 4, false, 50_000),
    _5TH(5, 3, false, 5_000),
    ;

    final int rank;
    final int price;
    final int count;
    final boolean bonus;

    Prize(int rank, int count, boolean bonus, int price) {
        this.rank = rank;
        this.price = price;
        this.bonus = bonus;
        this.count = count;
    }

    public static Optional<Prize> findPrize(int count, boolean bonus) {
        return Arrays.stream(Prize.values())
                .filter(prize -> prize.count == count && prize.bonus == bonus)
                .findFirst();
    }
}
