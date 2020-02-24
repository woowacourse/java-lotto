package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class ProfitCalculatorTest {
	static GameResult gameResult;

	static {
		Map<Rank, Integer> ranks = new HashMap<>();
		ranks.put(Rank.FIFTH, 3);
		ranks.put(Rank.THIRD, 2);
		gameResult = new GameResult(ranks);
	}

	@Test
	void getProfit() {
		// given
		Money purchaseMoney = new Money(10000);
		double expected = 30150.0d;
		// when
		double result = ProfitCalculator.getProfit(purchaseMoney, gameResult);
		//then
		assertThat(result).isEqualTo(expected);
	}
}