package lotto.domain.result;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import lotto.domain.PurchaseMoney;

class GameResultTest {

	public static final int SECOND = 4;

	@Test
	void of() {
		GameResult gameResult = new GameResult();
		RankCount rank = gameResult.of(Statistic.SIX);
		assertThat(rank.getStatistic()).isEqualTo(Statistic.SIX);
	}

	@Test
	void getEarningMoney() {
		GameResult gameResult = new GameResult();
		gameResult.of(Statistic.FIVE).plusCount();

		assertThat(
			gameResult.getEarningMoney(new PurchaseMoney("1000"))
		).isEqualTo((Statistic.FIVE.getPrize() / 1000) * 100);
	}

	@Test
	void getResult() {
		assertThat(new GameResult().getResult().size() == 5);
	}
}