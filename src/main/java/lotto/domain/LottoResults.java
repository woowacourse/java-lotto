package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LottoResults {
	private static final long DEFAULT_COUNT = 0;

	private final Map<LottoRank, Long> lottoResults;

	public LottoResults(List<LottoRank> lottoRanks) {
		validate(lottoRanks);
		lottoResults = lottoRanks.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		Arrays.stream(LottoRank.values())
				.forEach(rank -> lottoResults.putIfAbsent(rank, DEFAULT_COUNT));
	}

	private void validate(List<LottoRank> lottoRanks) {
		if (lottoRanks == null) {
			throw new IllegalArgumentException();
		}
	}

	public long getRankCount(LottoRank lottoRank) {
		return lottoResults.get(lottoRank);
	}
}
