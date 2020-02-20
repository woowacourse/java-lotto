package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class LottoResultTest {
	@Test
	void calculateTotalPrize() {
		Map<LottoRank, Long> rankResult = new HashMap<>();
		rankResult.put(LottoRank.FIRST, 10L);
		rankResult.put(LottoRank.FIFTH, 10L);
		rankResult.put(LottoRank.FOURTH, 10L);
		LottoResult result = new LottoResult(rankResult);
		long expected = (2_000_000_000L + 5_000L + 50_000L) * 10L;
		assertThat(result.calculateTotalPrize()).extracting("prize").isEqualTo(expected);
	}
}
