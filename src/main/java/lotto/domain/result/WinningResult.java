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
		this.winningResult = initializeRankResult(new ArrayList<>(Objects.requireNonNull(lottoRanks)));
	}

	private Map<LottoRank, Long> initializeRankResult(List<LottoRank> lottoRanks) {
		Map<LottoRank, Long> result = lottoRanks.stream()
			.collect(Collectors.groupingBy(Function.identity(), TreeMap::new, Collectors.counting()));
		putRankResultIfAbsent(result);
		return Collections.unmodifiableMap(result);
	}

	private void putRankResultIfAbsent(Map<LottoRank, Long> result) {
		Arrays.stream(LottoRank.values())
			.forEach(rank -> result.putIfAbsent(rank, 0L));
	}

	public long calculateProfitRate() {
		Money totalPrize = calculateTotalPrize();
		Money totalMoney = calculateTotalMoney();
		return totalPrize.calculateProfitRate(totalMoney);
	}

	private Money calculateTotalPrize() {
		return winningResult.keySet().stream()
			.map(rank -> rank.calculateTotalMoney(winningResult.get(rank)))
			.reduce(Money.valueOf(0), Money::plus);
	}

	private Money calculateTotalMoney() {
		return winningResult.keySet().stream()
			.map(winningResult::get)
			.map(count -> Money.valueOf(Money.UNIT * count))
			.reduce(Money.valueOf(0), Money::plus);
	}

	public Map<LottoRank, Long> getWinningResult() {
		return winningResult;
	}
}
