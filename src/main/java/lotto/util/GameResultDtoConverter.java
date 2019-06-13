package lotto.util;

import lotto.dto.GameResultDto;
import lotto.domain.GameResultMatcher;
import lotto.domain.LottoMachine;
import lotto.domain.Rank;

import java.util.HashMap;
import java.util.Map;

public class GameResultDtoConverter {
	public GameResultDto convertResultToDto(final GameResultMatcher gameResultMatcher) {
		Map<Rank, Integer> counts = new HashMap<>();
		for (Rank rank : Rank.values()) {
			counts.put(rank, gameResultMatcher.getRankCount(rank));
		}
		return GameResultDto.of(counts, gameResultMatcher.profit(LottoMachine.LOTTO_MONEY));
	}
}
