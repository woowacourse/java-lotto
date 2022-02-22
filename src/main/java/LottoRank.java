import java.util.function.BiFunction;
import java.util.stream.Stream;

public enum LottoRank {
    FIRST(2_000_000_000, LottoRank::isFirstPrize),
    SECOND(30_000_000, LottoRank::isSecondPrize),
    THIRD(1_500_000, LottoRank::isThirdPrize),
    FOURTH(50_000, LottoRank::isFourthPrize),
    FIFTH(5_000, LottoRank::isFifthPrize),
    NOTHING(0, LottoRank::isNothingPrize);

    private int prizeAmount;
    private BiFunction<Long, Boolean, Boolean> predicate;

    LottoRank(int prizeAmount, BiFunction<Long, Boolean, Boolean> predicate) {
        this.prizeAmount = prizeAmount;
        this.predicate = predicate;
    }

    private boolean isMatched(long matchCount, boolean bonusMatch) {
        return predicate.apply(matchCount, bonusMatch);
    }

    public static LottoRank of(long matchCount, boolean bonusMatch) {
        return findLottoRank(matchCount, bonusMatch);
    }

    private static LottoRank findLottoRank(long matchCount, boolean bonusMatch) {
        return Stream.of(values())
            .filter(rank -> rank.isMatched(matchCount, bonusMatch))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("일치 갯수는 0이상 6이하이여야 합니다."));
    }

    private static boolean isFirstPrize(long matchCount, boolean bonusMatch) {
        return matchCount == 6;
    }

    private static boolean isSecondPrize(long matchCount, boolean bonusMatch) {
        return matchCount == 5 && bonusMatch;
    }

    private static boolean isThirdPrize(long matchCount, boolean bonusMatch) {
        return matchCount == 5 && !bonusMatch;
    }

    private static boolean isFourthPrize(long matchCount, boolean bonusMatch) {
        return matchCount == 4;
    }

    private static boolean isFifthPrize(long matchCount, boolean bonusMatch) {
        return matchCount == 3;
    }

    private static boolean isNothingPrize(long matchCount, boolean bonusMatch) {
        return 0 <= matchCount && matchCount < 3;
    }
}
