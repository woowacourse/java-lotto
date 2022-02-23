package domain;

import java.util.Arrays;
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
    private final int prize;

    LottoRank(int matchNumber, int prize) {
        this.matchNumber = matchNumber;
        this.prize = prize;
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

    public static List<LottoRank> valuesWithPrize() {
        return Arrays.stream(LottoRank.values())
                .filter(rank -> rank != NOTHING)
                .collect(Collectors.toList());
    }

    private static LottoRank checkSecondOrThird(boolean bonus) {
        if (bonus) {
            return LottoRank.SECOND;
        }
        return LottoRank.THIRD;
    }

    public int getPrize() {
        return prize;
    }

    public int getMatchNumber() {
        return matchNumber;
    }
}
