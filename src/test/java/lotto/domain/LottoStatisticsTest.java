package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class LottoStatisticsTest {
	@Test
	void constructor() {
		LottoPurchaseMoney lottoPurchaseMoney = new LottoPurchaseMoney("14000");
		LottoResults lottoResults = new LottoResults(Arrays.asList(
				LottoRank.FIRST,
				LottoRank.FIRST,
				LottoRank.MISS
		));
		assertThat(new LottoStatistics(lottoPurchaseMoney, lottoResults)).isInstanceOf(LottoStatistics.class);
	}
}
