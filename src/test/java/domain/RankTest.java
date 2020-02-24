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
				-123, Rank.NO_RANK
			),
			Arguments.of(
				2000, Rank.NO_RANK
			),
			Arguments.of(
				1, Rank.NO_RANK
			),
			Arguments.of(
				2, Rank.NO_RANK
			),
			Arguments.of(
				3, Rank.FIFTH
			),
			Arguments.of(
				4, Rank.FOURTH
			),
			Arguments.of(
				5, Rank.THIRD
			),
			Arguments.of(
				6, Rank.FIRST
			)
		);
	}

	@ParameterizedTest
	@MethodSource("createCountAndRank")
	void of(int count, Rank expected) {
		assertThat(Rank.of(count)).isEqualTo(expected);
	}
}