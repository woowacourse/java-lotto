package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WinningResultTest {
	private WinningResult result;

	@BeforeEach
	void setup() {
		Map<LottoRank, Long> rankResult = new HashMap<>();
		rankResult.put(LottoRank.FIRST, 10L);
		rankResult.put(LottoRank.FIFTH, 10L);
		rankResult.put(LottoRank.FOURTH, 10L);
		result = new WinningResult(rankResult);
	}

	@Test
	void calculateTotalPrize() {
		long expected = (2_000_000_000L + 5_000L + 50_000L) * 10L;
		assertThat(result.calculateTotalMoney()).extracting("money").isEqualTo(expected);
	}

	@Test
	void calculateProfitsRate() {
		Money money = Money.of(2_000_000_000);
		long expected = (2_000_000_000L + 5_000L + 50_000L) * 10L * 100 / 2_000_000_000;
		assertThat(result.getProfitRate(money)).isEqualTo(expected);
	}
}
