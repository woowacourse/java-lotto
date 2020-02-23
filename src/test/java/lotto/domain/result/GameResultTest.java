package lotto.domain.result;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import lotto.domain.PurchaseMoney;

class GameResultTest {

	@Test
	void of() {
		GameResult gameResult = new GameResult();
		Optional<RankCount> rank = gameResult.of(Optional.of(Statistic.SIX));
		assertThat(rank.get().getStatistic()).isEqualTo(Statistic.SIX);
	}

	@Test
	void getEarningMoney() {
		GameResult gameResult = new GameResult();
		gameResult.getResult().get(4).plusCount();

		assertThat(
			gameResult.getEarningMoney(new PurchaseMoney("1000"))
		).isEqualTo((Statistic.SIX.getPrize() / 1000) * 100);
	}

	@Test
	void getResult() {
		assertThat(new GameResult().getResult().size() == 5);
	}
}