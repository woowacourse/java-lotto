package lotto.domain;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WinningLotto {
	private static final String DUPLICATE_EXCEPTION_MESSAGE = "당첨 번호와 보너스 볼이 중복되었습니다.";

	private final LottoTicket winningLottoTicket;
	private final Ball bonusBall;

	public WinningLotto(LottoTicket winningLottoTicket, Ball bonusBall) {
		validateDuplication(winningLottoTicket, bonusBall);
		this.winningLottoTicket = winningLottoTicket;
		this.bonusBall = bonusBall;
	}

	private void validateDuplication(LottoTicket winningLottoTicket, Ball bonusBall) {
		if (winningLottoTicket.contains(bonusBall)) {
			throw new IllegalArgumentException(DUPLICATE_EXCEPTION_MESSAGE);
		}
	}

	public LottoResult getResult(LottoTickets lottoTickets) {
		Map<LottoRank, Long> result = initializeRankResult(lottoTickets);
		putRankResultIfAbsent(result);
		return new LottoResult(result);
	}

	private Map<LottoRank, Long> initializeRankResult(LottoTickets lottoTickets) {
		return lottoTickets.getLottoTickets().stream()
			.filter(lotto -> LottoRank.isValidMatchCount(lotto.findMatchBallCount(winningLottoTicket)))
			.map(lotto -> LottoRank.findRank(lotto.findMatchBallCount(winningLottoTicket), lotto.contains(bonusBall)))
			.collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
	}

	private void putRankResultIfAbsent(Map<LottoRank, Long> result) {
		Arrays.stream(LottoRank.values())
			.forEach(rank -> result.putIfAbsent(rank, 0L));
	}
}
