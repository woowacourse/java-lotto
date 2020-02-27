package lotto.domain.result;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import lotto.domain.ticket.Money;

public class WinningResult {
	private final Map<LottoRank, Long> winningResult;

	public WinningResult(List<LottoRank> lottoRanks) {
		validateNull(lottoRanks);
		this.winningResult = initializeRankResult(new ArrayList<>(lottoRanks));
	}

	private void validateNull(List<LottoRank> lottoRanks) {
		if (Objects.isNull(lottoRanks)) {
			throw new NullPointerException();
		}
	}

	private Map<LottoRank, Long> initializeRankResult(List<LottoRank> lottoRanks) {
		Map<LottoRank, Long> result = lottoRanks.stream()
			.filter(LottoRank::isPrizingRank)
			.collect(Collectors.groupingBy(Function.identity(), TreeMap::new, Collectors.counting()));
		putRankResultIfAbsent(result);
		return Collections.unmodifiableMap(result);
	}

	private void putRankResultIfAbsent(Map<LottoRank, Long> result) {
		Arrays.stream(LottoRank.values())
			.filter(LottoRank::isPrizingRank)
			.forEach(rank -> result.putIfAbsent(rank, 0L));
	}

	public Money calculateTotalMoney() {
		return winningResult.keySet().stream()
			.map(rank -> rank.calculateTotalMoney(winningResult.get(rank)))
			.reduce(Money.valueOf(0), Money::plus);
	}

	public Map<LottoRank, Long> getWinningResult() {
		return winningResult;
	}
}
