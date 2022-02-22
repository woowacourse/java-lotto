import java.util.function.BiFunction;
import java.util.stream.Stream;

public enum LottoRank {
    FIRST(new Prize(2_000_000_000), LottoRank::isFirstPrize),
    SECOND(new Prize(30_000_000), LottoRank::isSecondPrize),
    THIRD(new Prize(1_500_000), LottoRank::isThirdPrize),
    FOURTH(new Prize(50_000), LottoRank::isFourthPrize),
    FIFTH(new Prize(5_000), LottoRank::isFifthPrize),
    NOTHING(Prize.ZERO, LottoRank::isNothingPrize);

    private final Prize prize;
    private final BiFunction<Long, Boolean, Boolean> predicate;

    LottoRank(Prize prize, BiFunction<Long, Boolean, Boolean> predicate) {
        this.prize = prize;
        this.predicate = predicate;
    }

    public Prize getPrize() {
        return prize;
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
