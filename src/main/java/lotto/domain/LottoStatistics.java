package lotto.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoStatistics {
	private final LottoPurchaseMoney lottoPurchaseMoney;
	private final LottoResults lottoResults;

	public LottoStatistics(LottoPurchaseMoney lottoPurchaseMoney, LottoResults lottoResults) {
		validate(lottoPurchaseMoney, lottoResults);
		this.lottoPurchaseMoney = lottoPurchaseMoney;
		this.lottoResults = lottoResults;
	}

	private void validate(LottoPurchaseMoney lottoPurchaseMoney, LottoResults lottoResults) {
		if (lottoPurchaseMoney == null || lottoResults == null) {
			throw new IllegalArgumentException();
		}
	}

	public Map<LottoRank, Long> getLottoRanksCount() {
		Map<LottoRank, Long> lottoRanksCount = new LinkedHashMap<>();
		Arrays.stream(LottoRank.values())
				.filter(rank -> rank != LottoRank.MISS)
				.collect(Collectors.toCollection(LinkedList::new))
				.descendingIterator()
				.forEachRemaining(rank -> lottoRanksCount.put(rank, lottoResults.getRankCount(rank)));
		return Collections.unmodifiableMap(lottoRanksCount);
	}

	public long getProfitRate() {
		return sumWinnings() / lottoPurchaseMoney.getValue() * 100;
	}

	private long sumWinnings() {
		return Arrays.stream(LottoRank.values())
				.mapToLong(rank -> rank.getWinnings() * lottoResults.getRankCount(rank))
				.sum();
	}
}
