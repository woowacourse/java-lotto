package lotto.util;

import lotto.dto.GameStatDto;
import lotto.domain.GameResultMatcher;
import lotto.domain.LottoMachine;
import lotto.domain.Rank;

import java.util.HashMap;
import java.util.Map;

public class GameResultDtoConverter {
	public GameStatDto convertResultToDto(final GameResultMatcher gameResultMatcher) {
		Map<Rank, Integer> counts = new HashMap<>();
		for (Rank rank : Rank.values()) {
			counts.put(rank, gameResultMatcher.getRankCount(rank));
		}
		return GameStatDto.of(counts, gameResultMatcher.profit(LottoMachine.LOTTO_MONEY));
	}
}
