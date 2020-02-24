package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GameResultTest {

	static GameResult gameResult;

	static {
		Map<Rank, Integer> ranks = new HashMap<>();
		ranks.put(Rank.FIFTH, 3);
		ranks.put(Rank.THIRD, 2);
		gameResult = new GameResult(ranks);
	}

	private static Stream<Arguments> createRankAndCount() {
		return Stream.of(
			Arguments.of(Rank.FIFTH, 3),
			Arguments.of(Rank.THIRD, 2),
			Arguments.of(Rank.NO_RANK, 0)
		);
	}

	@Test
	void getResultMoney() {
		// given
		Money resultMoney = gameResult.getResultMoney();
		Money expected = new Money(3015000);
		// then
		assertThat(resultMoney).isEqualTo(expected);
	}

	@ParameterizedTest
	@MethodSource("createRankAndCount")
	void getMatched(Rank rank, int expected) {
		// given
		int givenCount = gameResult.numberOfRank(rank);
		// then
		assertThat(givenCount).isEqualTo(expected);
	}
}