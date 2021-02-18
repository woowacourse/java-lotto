package lotto.domain;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.function.BiFunction;

public enum Rank {
    FIRST_PLACE(BigInteger.valueOf(2_000_000_000), (val, isBonus) -> val == 6),
    SEC0ND_PLACE(BigInteger.valueOf(30_000_000), (val, isBonus) -> val == 5 && isBonus),
    THIRD_PLACE(BigInteger.valueOf(1_500_000), (val, isBonus) -> val == 5 && !isBonus),
    FOURTH_PLACE(BigInteger.valueOf(50_000), (val, isBonus) -> val == 4),
    FIFTH_PLACE(BigInteger.valueOf(5_000), (val, isBonus) -> val == 3),
    UNRANKED(BigInteger.valueOf(0), (val, isBonus) -> val < 3);

    private final BiFunction<Integer, Boolean, Boolean> biFunction;
    private final BigInteger prize;

    Rank(BigInteger prize, BiFunction<Integer, Boolean, Boolean> biFunction) {
        this.biFunction = biFunction;
        this.prize = prize;
    }

    public static Rank getInstance(int val, boolean isBonus) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.biFunction.apply(val, isBonus))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(""));
    }

    public BigInteger getPrize() {
        return this.prize;
    }
}