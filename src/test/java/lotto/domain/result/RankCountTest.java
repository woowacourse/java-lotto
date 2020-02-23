package lotto.domain.result;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RankCountTest {

	@Test
	void plusCount() {
		RankCount rankCount = new RankCount(Statistic.SIX);
		rankCount.plusCount();
		assertThat(rankCount.getCount()).isEqualTo(1);
	}

	@Test
	void getProfit() {
		RankCount rankCount = new RankCount(Statistic.SIX);
		rankCount.plusCount();
		assertThat(rankCount.getProfit()).isEqualTo(2_000_000_000);
	}

	@Test
	void getStatistic() {
		RankCount rankCount = new RankCount(Statistic.BONUS);
		assertThat(rankCount.getStatistic()).isEqualTo(Statistic.BONUS);
	}
}