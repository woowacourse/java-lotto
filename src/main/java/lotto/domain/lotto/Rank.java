package lotto.domain.lotto;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.function.BiFunction;

public enum Rank {
    FIRST_PLACE((matchCount, isBonus) -> matchCount == 6,
            BigInteger.valueOf(2_000_000_000)),
    SEC0ND_PLACE((matchCount, isBonus) -> matchCount == 5 && isBonus,
            BigInteger.valueOf(30_000_000)),
    THIRD_PLACE((matchCount, isBonus) -> matchCount == 5 && !isBonus,
            BigInteger.valueOf(1_500_000)),
    FOURTH_PLACE((matchCount, isBonus) -> matchCount == 4,
            BigInteger.valueOf(50_000)),
    FIFTH_PLACE((matchCount, isBonus) -> matchCount == 3,
            BigInteger.valueOf(5_000)),
    UNRANKED((matchCount, isBonus) -> matchCount < 3 && matchCount >= 0,
            BigInteger.ZERO);

    private final BiFunction<Integer, Boolean, Boolean> matchFunction;
    private final BigInteger prize;

    Rank(BiFunction<Integer, Boolean, Boolean> matchFunction, BigInteger prize) {
        this.matchFunction = matchFunction;
        this.prize = prize;
    }

    public static Rank matchRank(int lottoNumberMatchCount, boolean isBonusBallMatches) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.matchFunction.apply(lottoNumberMatchCount, isBonusBallMatches))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 조건입니다. 당첨금을 계산할 수 없습니다."));
    }

    public BigInteger getPrize() {
        return this.prize;
    }
}