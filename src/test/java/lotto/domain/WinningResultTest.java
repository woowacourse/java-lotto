package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningResultTest {
	private WinningResult result;

	@BeforeEach
	void setup() {
		Map<LottoRank, Long> rankResult = new HashMap<>();
		rankResult.put(LottoRank.FIRST, 10L);
		rankResult.put(LottoRank.SECOND, 0L);
		rankResult.put(LottoRank.THIRD, 0L);
		rankResult.put(LottoRank.FIFTH, 10L);
		rankResult.put(LottoRank.FOURTH, 10L);
		result = new WinningResult(rankResult);
	}

	@DisplayName("당첨 결과에 포함되어있는 총 삼금 계산 확인")
	@Test
	void calculateTotalPrize() {
		long expected = (2_000_000_000L + 5_000L + 50_000L) * 10L;
		assertThat(result.calculateTotalMoney()).extracting("money").isEqualTo(expected);
	}

	@DisplayName("당첨 정보를 통해 수익률 계산 테스트")
	@Test
	void calculateProfitsRate() {
		Money money = Money.valueOf(2_000_000_000);
		long expected = (2_000_000_000L + 5_000L + 50_000L) * 10L * 100 / 2_000_000_000;
		assertThat(result.getProfitRate(money)).isEqualTo(expected);
	}
}
