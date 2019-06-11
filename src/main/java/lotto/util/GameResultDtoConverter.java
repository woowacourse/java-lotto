package lotto.util;

import lotto.GameResultDto;
import lotto.domain.GameResult;
import lotto.domain.LottoMachine;
import lotto.domain.Rank;

import java.util.HashMap;
import java.util.Map;

public class GameResultDtoConverter {
	public GameResultDto convertResultToDto(final GameResult gameResult) {
		Map<Rank, Integer> counts = new HashMap<>();
		for (Rank rank : Rank.values()) {
			counts.put(rank, gameResult.getRankCount(rank));
		}
		return GameResultDto.of(counts, gameResult.profit(LottoMachine.LOTTO_MONEY));
	}
}
