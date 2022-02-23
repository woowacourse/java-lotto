package lotto.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.xml.transform.Result;

public enum Rank {

    FIRST(2000000000, (hitCounts, bonus) -> hitCounts == 6),
    SECOND(30000000, (hitCounts, bonus) -> hitCounts == 5 && bonus),
    THIRD(1500000, (hitCounts, bonus) -> hitCounts == 5 && !bonus),
    FOURTH(50000, (hitCounts, bonus) -> hitCounts == 4),
    FIFTH(5000, (hitCounts, bonus) -> hitCounts == 3),
    NOT_THING(0, (hitCounts, bonus) -> hitCounts < 3 && hitCounts >= 0),
    ;

    private final long reward;
    private final BiFunction<Integer, Boolean, Boolean> expression;

    Rank(final long reward, final BiFunction<Integer, Boolean, Boolean> expression) {
        this.reward = reward;
        this.expression = expression;
    }

    public static long calculateMoney(Rank currentRank, long count) {
        return currentRank.reward * count;
    }

    public static Rank calculateCurrentRank(int hitCounts, boolean bonus) {
        return Arrays.stream(values())
                .filter(rank -> rank.expression.apply(hitCounts, bonus))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당하는 랭크가 없습니다."));
    }

    public static Map<Rank, Integer> initResultMap() {
        return Arrays.stream(values())
                .collect(Collectors.toMap(rank -> rank, Rank::defaultCount));
    }

    private int defaultCount() {
        return 0;
    }
}
