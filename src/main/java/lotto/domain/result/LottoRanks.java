package lotto.domain.result;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public class LottoRanks {
	private final List<LottoRank> lottoRanks;

	public LottoRanks(List<LottoRank> lottoRanks) {
		this.lottoRanks = Objects.requireNonNull(lottoRanks);
	}

	public WinningResult calculateWinningResult() {
		Map<LottoRank, Long> result = lottoRanks.stream()
			.collect(groupingBy(Function.identity(), HashMap::new, counting()));
		putRankResultIfAbsent(result);
		return new WinningResult(result);
	}

	private void putRankResultIfAbsent(Map<LottoRank, Long> result) {
		Arrays.stream(LottoRank.values())
			.forEach(rank -> result.putIfAbsent(rank, 0L));
	}
}
