package lotto.domain.reword;

import java.util.*;

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
        if (Objects.isNull(rewords.get(reword))) {
            return EMPTY_REWORD;
        }

        return rewords.get(reword);
    }

    public double profit(final int money) {
        long profit = EMPTY;

        for (Reword key : rewords.keySet()) {
            profit += multiply(key.getWinningMoney(), rewords.get(key));
        }

        return divide(money, profit);
    }

    private long multiply(final int winningMoney, final int count) {
        return (long) winningMoney * count;
    }

    private double divide(final long money, final long winningMoney) {
        return (double) winningMoney / (double) money;
    }
}
