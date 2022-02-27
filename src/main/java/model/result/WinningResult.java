package model.result;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class WinningResult {
    private static final int INIT_COUNT = 0;
    private static final int ADD_COUNT = 1;

    private final Map<Rank, Integer> winningCount = new EnumMap<>(Rank.class);

    public WinningResult() {
        Arrays.stream(Rank.values())
                .forEach(number -> winningCount.put(number, INIT_COUNT));
    }

    public void addCount(Rank rank) {
        winningCount.put(rank, winningCount.get(rank) + ADD_COUNT);
    }

    public Map<Rank, Integer> getWinningCount() {
        return winningCount;
    }
}
