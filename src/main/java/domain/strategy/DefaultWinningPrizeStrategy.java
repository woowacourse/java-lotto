package domain.strategy;

import domain.WinningPrize;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class DefaultWinningPrizeStrategy implements WinningPrizeStrategy {
    private static final int SECOND_PRIZE_MATCH_COUNT = 5;

    private static final Map<Integer, WinningPrize> MATCH_COUNT_WINNING_PRIZE_INFO = new HashMap<>() {{
        put(6, WinningPrize.FIRST);
        put(5, WinningPrize.THIRD);
        put(4, WinningPrize.FOURTH);
        put(3, WinningPrize.FIFTH);
    }};

    private static final Map<WinningPrize, Integer> WINNING_PRIZE_MATCH_COUNT_INFO = new EnumMap<>(
            WinningPrize.class) {{
        put(WinningPrize.FIRST, 6);
        put(WinningPrize.SECOND, 5);
        put(WinningPrize.THIRD, 5);
        put(WinningPrize.FOURTH, 4);
        put(WinningPrize.FIFTH, 3);
        put(WinningPrize.NONE, 0);
    }};

    @Override
    public WinningPrize findWinningPrize(int matchCount, boolean matchBonus) {
        if (matchCount == SECOND_PRIZE_MATCH_COUNT && matchBonus) {
            return WinningPrize.SECOND;
        }
        return MATCH_COUNT_WINNING_PRIZE_INFO.getOrDefault(matchCount, WinningPrize.NONE);
    }

    @Override
    public int findMatchCount(WinningPrize winningPrize) {
        return WINNING_PRIZE_MATCH_COUNT_INFO.getOrDefault(winningPrize, 0);
    }

    @Override
    public boolean findMatchBonus(WinningPrize winningPrize) {
        return winningPrize.equals(WinningPrize.SECOND);
    }
}
