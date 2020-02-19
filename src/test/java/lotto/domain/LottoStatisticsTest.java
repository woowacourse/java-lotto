package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class LottoStatisticsTest {
	@Test
	void constructor() {
		Map<LottoRank, Long> statistics = new LinkedHashMap<>();
		statistics.put(LottoRank.FIRST, 1L);
		statistics.put(LottoRank.THIRD, 3L);
		statistics.put(LottoRank.FIFTH, 2L);
		statistics.put(LottoRank.MISS, 5L);
		assertThat(new LottoStatistics(statistics)).isInstanceOf(LottoStatistics.class);
	}
}
