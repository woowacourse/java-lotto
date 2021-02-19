package lotto.domain;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Rewords {

    private static final int EMPTY_REWORD = 0;
    private static final int EMPTY = 0;

    private final Map<Reword, Integer> rewords;

    public Rewords(final List<Reword> rewords) {
        this.rewords = createRewordMap(rewords);
    }

    private Map<Reword, Integer> createRewordMap(final List<Reword> rewords) {
        final Map<Reword, Integer> rewordMap = new EnumMap<>(Reword.class);

        for (Reword reword : rewords) {
            rewordMap.putIfAbsent(reword, EMPTY_REWORD);
            rewordMap.put(reword, rewordMap.get(reword) + 1);
        }

        return Collections.unmodifiableMap(rewordMap);
    }

    public int countOfReword(final Reword reword) {
        if (rewords.get(reword) != null) {
            return rewords.get(reword);
        }

        return EMPTY_REWORD;
    }

    public double profit(final int money) {
        long profit = EMPTY;

        for (Reword key : rewords.keySet()) {
            profit += (long) key.getWinningMoney() * rewords.get(key);
        }

        return divide(money, profit);
    }

    private double divide(final long money, final long winningMoney) {
        return (double) winningMoney / (double) money;
    }
}
