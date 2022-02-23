package domain;

import java.util.EnumMap;
import java.util.Map;

public class WinningResult {

	private final EnumMap<LottoRank, Integer> winningResult;

	public static WinningResult initializeWinningResult() {
		return new WinningResult(new EnumMap<>(Map.ofEntries(
			Map.entry(LottoRank.FIRST, 0),
			Map.entry(LottoRank.SECOND, 0),
			Map.entry(LottoRank.THIRD, 0),
			Map.entry(LottoRank.FOURTH, 0),
			Map.entry(LottoRank.FIFTH, 0),
			Map.entry(LottoRank.FAIL, 0)
		)));
	}

	private WinningResult(EnumMap<LottoRank, Integer> winningResult) {
		this.winningResult = winningResult;
	}

	public EnumMap<LottoRank, Integer> getWinningResult() {
		return winningResult;
	}

	public void addResult(LottoRank rank) {
		winningResult.put(rank, winningResult.get(rank) + 1);
	}
}
