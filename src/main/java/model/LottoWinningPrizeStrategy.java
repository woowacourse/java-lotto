package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

public class LottoWinningPrizeStrategy implements WinningPrizeStrategy {

    private static final Map<Integer, WinningPrize> MATCH_COUNT_WINNING_PRIZE_INFO = new HashMap<>() {{
        put(6, WinningPrize.FIRST);
        put(5, WinningPrize.THIRD);
        put(4, WinningPrize.FOURTH);
        put(3, WinningPrize.FIFTH);
    }};

    @Override
    public WinningPrize winningPrize(int matchCount, boolean matchBonus) {
        if (matchCount == 5 && matchBonus) {
            return WinningPrize.SECOND;
        }
        return MATCH_COUNT_WINNING_PRIZE_INFO.get(matchCount);
    }

    @Override
    public int matchCount(WinningPrize winningPrize) {
        return MATCH_COUNT_WINNING_PRIZE_INFO.entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(winningPrize))
                .map(Entry::getKey)
                .findAny()
                .orElse(0);
    }

    @Override
    public boolean matchBonus(WinningPrize winningPrize) {
        return winningPrize.equals(WinningPrize.SECOND);
    }
}
