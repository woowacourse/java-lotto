package lotto.domain.lotto;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.function.BiFunction;

public enum Rank {
    FIRST_PLACE((val, isBonus) -> val == 6,
            BigInteger.valueOf(2_000_000_000)),
    SEC0ND_PLACE((val, isBonus) -> val == 5 && isBonus,
            BigInteger.valueOf(30_000_000)),
    THIRD_PLACE((val, isBonus) -> val == 5 && !isBonus,
            BigInteger.valueOf(1_500_000)),
    FOURTH_PLACE((val, isBonus) -> val == 4,
            BigInteger.valueOf(50_000)),
    FIFTH_PLACE((val, isBonus) -> val == 3,
            BigInteger.valueOf(5_000)),
    UNRANKED((val, isBonus) -> val < 3,
            BigInteger.valueOf(0));

    private final BiFunction<Integer, Boolean, Boolean> biFunction;
    private final BigInteger prize;

    Rank(BiFunction<Integer, Boolean, Boolean> biFunction, BigInteger prize) {
        this.biFunction = biFunction;
        this.prize = prize;
    }

    public static Rank getInstance(int val, boolean isBonus) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.biFunction.apply(val, isBonus))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("")); //todo 커스텀 예외
    }

    public BigInteger getPrize() {
        return this.prize;
    }
}