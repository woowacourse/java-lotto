package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoTicket {

	private static final int DEFAULT_RANK_COUNT = 0;

	private final List<Lotto> lottoTicket;

	public LottoTicket(List<Lotto> lottoTicket) {
		this.lottoTicket = new ArrayList<>(lottoTicket);
	}

	public EnumMap<LottoRank, Integer> checkLottoTicketWinningResult(WinningNumbers winningNumbers) {
		EnumMap<LottoRank, Integer> winningResult = initializeWinningResult();
		Lotto winningNumber = winningNumbers.getWinningNumbers();
		Number bonusNumber = winningNumbers.getBonusNumber();

		for (Lotto lotto : lottoTicket) {
			LottoRank rank = lotto.checkWinningResult(winningNumber, bonusNumber);
			addWinningResultCount(winningResult, rank);
		}
		return winningResult;
	}

	private EnumMap<LottoRank, Integer> initializeWinningResult() {
		return new EnumMap<>(Map.ofEntries(
			Map.entry(LottoRank.FIRST, DEFAULT_RANK_COUNT),
			Map.entry(LottoRank.SECOND, DEFAULT_RANK_COUNT),
			Map.entry(LottoRank.THIRD, DEFAULT_RANK_COUNT),
			Map.entry(LottoRank.FOURTH, DEFAULT_RANK_COUNT),
			Map.entry(LottoRank.FIFTH, DEFAULT_RANK_COUNT)
		));
	}

	private static void addWinningResultCount(EnumMap<LottoRank, Integer> winningResult, LottoRank lottoRank) {
		if (lottoRank == LottoRank.FAIL) {
			return;
		}
		winningResult.compute(lottoRank, (rank, count) -> count + 1);
	}

	public List<Lotto> getLottoTicket() {
		return Collections.unmodifiableList(lottoTicket);
	}
}
