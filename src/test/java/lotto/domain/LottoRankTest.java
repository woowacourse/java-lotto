package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class LottoRankTest {

	@Test
	void getRank() {
		assertThat(LottoRank.getRank(3)).isEqualTo(LottoRank.FIFTH);
	}

	@Test
	void getTotal() {
		long totalPrize = LottoRank.FIRST.getTotal(3);
		long expected = 2000000000 * 3;
		assertThat(totalPrize).isEqualTo(expected);
	}
}
