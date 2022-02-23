package domain;

import java.util.Arrays;

//```text
//        - 3개 -> 5천원
//        - 4개 -> 5만원
//        - 5개 -> 15만원
//        - 5개 + 보너스 볼 ->  3천만원
//        - 6개 -> 20억
//        ```
public enum Rank {
    FIRST(2000000000, 6, false),
    SECOND(30000000, 5, true),
    THIRD(150000, 5, false),
    FOUR(50000, 4, false),
    FIVE(5000, 3, false),
    NOTHING(0, 0, false);

    private final int money;
    private final int match;
    private final boolean bonus;

    Rank(int money, int match, boolean bonus) {
        this.money = money;
        this.match = match;
        this.bonus = bonus;
    }

    public int getMoney() {
        return money;
    }

    public int getMatch() {
        return match;
    }

    public static Rank getRank(int target, boolean bonus) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.match == target)
                .filter(rank -> rank.bonus == bonus)
                .findFirst()
                .orElse(NOTHING);
    }
}
