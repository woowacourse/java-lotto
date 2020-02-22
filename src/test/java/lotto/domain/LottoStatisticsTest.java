package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class LottoStatisticsTest {
	private static LottoPurchaseMoney lottoPurchaseMoney;
	private static LottoResults lottoResults;

	@BeforeAll
	static void setUp() {
		lottoPurchaseMoney = new LottoPurchaseMoney("10000");
		lottoResults = new LottoResults(Arrays.asList(
				LottoRank.FIRST,
				LottoRank.FIRST,
				LottoRank.MISS,
				LottoRank.MISS,
				LottoRank.MISS,
				LottoRank.MISS,
				LottoRank.MISS,
				LottoRank.MISS,
				LottoRank.MISS,
				LottoRank.MISS
		));
	}

	@Test
	void constructor() {
		assertThat(new LottoStatistics(lottoPurchaseMoney, lottoResults)).isInstanceOf(LottoStatistics.class);
	}

	@Test
	void getProfitRate() {
		LottoStatistics lottoStatistics = new LottoStatistics(lottoPurchaseMoney, lottoResults);
		assertThat(lottoStatistics.getProfitRate()).isEqualTo(40000000);
	}

	@Test
	void getLottoRanksCount() {
		LottoStatistics lottoStatistics = new LottoStatistics(lottoPurchaseMoney, lottoResults);
		Map<LottoRank, Long> expected = new LinkedHashMap<>();
		expected.put(LottoRank.FIFTH, 0L);
		expected.put(LottoRank.FOURTH, 0L);
		expected.put(LottoRank.THIRD, 0L);
		expected.put(LottoRank.SECOND, 0L);
		expected.put(LottoRank.FIRST, 2L);
		assertThat(lottoStatistics.getLottoRanksCount()).isEqualTo(expected);
	}
}
