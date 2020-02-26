package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RankTest {
	static Stream<Arguments> createCountAndRank() {
		return Stream.of(
			Arguments.of(
				-123, true, Rank.NO_RANK
			),
			Arguments.of(
				2000, true, Rank.NO_RANK
			),
			Arguments.of(
				1, true, Rank.NO_RANK
			),
			Arguments.of(
				2, true, Rank.NO_RANK
			),
			Arguments.of(
				3, true, Rank.FIFTH
			),
			Arguments.of(
				4, true, Rank.FOURTH
			),
			Arguments.of(
				5, false, Rank.THIRD
			),
			Arguments.of(
				5, true, Rank.SECOND
			),
			Arguments.of(
				6, false, Rank.FIRST
			)
		);
	}

	private static Stream<Arguments> createRankAndCount() {
		return Stream.of(
			Arguments.of(
				Rank.SECOND, 2, 60_000_000d
			), Arguments.of(
				Rank.FIRST, 3, 6_000_000_000d
			), Arguments.of(
				Rank.FIFTH, 5, 25_000d
			)
		);
	}

	@ParameterizedTest
	@MethodSource("createCountAndRank")
	void of(int count, boolean bonusNumberMatch, Rank expected) {
		assertThat(Rank.of(count, bonusNumberMatch)).isEqualTo(expected);
	}

	@ParameterizedTest
	@MethodSource("createRankAndCount")
	void totalMoneyByRank(Rank rank, int count, double expected) {
		assertThat(rank.totalMoneyByRank(count)).isEqualTo(expected);
	}
}