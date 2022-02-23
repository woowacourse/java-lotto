package domain;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum LottoRank {

    FIRST(6, 2_000_000_000),
    SECOND(5,30_000_000),
    THIRD(5,1_500_000),
    FOURTH(4,50_000),
    FIFTH(3,5_000),
    NOTHING(0, 0);

    private final int matchNumber;
    private final int price;

    LottoRank(int matchNumber, int price) {
        this.matchNumber = matchNumber;
        this.price = price;
    }

    public static LottoRank valueOf(int count, boolean bonus) {
        if (count == SECOND.matchNumber) {
            return checkSecondOrThird(bonus);
        }

        return Arrays.stream(LottoRank.values())
                .filter(rank -> rank.matchNumber == count)
                .findFirst()
                .orElse(NOTHING);
    }

    private static LottoRank checkSecondOrThird(boolean bonus) {
        if (bonus) {
            return LottoRank.SECOND;
        }
        return LottoRank.THIRD;
    }

    public int getPrice() {
        return price;
    }

    public static List<LottoRank> valuesWithoutNothing() {
        return Arrays.stream(LottoRank.values())
                .filter(rank -> rank != NOTHING)
                .collect(Collectors.toList());
    }

    public int getMatchNumber() {
        return matchNumber;
    }
}
