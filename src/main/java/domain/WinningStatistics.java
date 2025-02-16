package domain;

import java.util.Map;

public record WinningStatistics(Map<LottoPrize, Integer> prizeCounter) {
}
