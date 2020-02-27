package lotto.domain.result;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class RankTest {
	@ParameterizedTest
	@MethodSource("generateRankMatchingInput")
	void matching(int matchingNumberCount, boolean isBonusMatching, Rank expected) {
		// when
		Rank result = Rank.matching(matchingNumberCount, isBonusMatching);

		// then
		Assertions.assertThat(result).isEqualTo(expected);
	}

	static Stream<Arguments> generateRankMatchingInput() {
		return Stream.of(Arguments.of(6, true, Rank.FIRST_PLACE),
				Arguments.of(6, false, Rank.FIRST_PLACE),
				Arguments.of(5, true, Rank.SECOND_PLACE),
				Arguments.of(5, false, Rank.THIRD_PLACE),
				Arguments.of(4, true, Rank.FOURTH_PLACE),
				Arguments.of(4, false, Rank.FOURTH_PLACE),
				Arguments.of(3, true, Rank.FIFTH_PLACE),
				Arguments.of(3, false, Rank.FIFTH_PLACE),
				Arguments.of(2, true, Rank.NONE),
				Arguments.of(2, false, Rank.NONE),
				Arguments.of(7, true, Rank.NONE),
				Arguments.of(7, false, Rank.NONE));
	}

	@ParameterizedTest
	@MethodSource("generateRewardInput")
	void calculateReward() {
		// given
		Rank rank = Rank.SECOND_PLACE;
		int count = 3;

		// when
		int reward = rank.calculateReward(count);

		// then
		int expected = 900_000_000;
		Assertions.assertThat(reward).isEqualTo(expected);
	}

	static Stream<Arguments> generateRewardInput() {
		return Stream.of(Arguments.of(Rank.FIRST_PLACE, 1, 2_000_000_000),
				Arguments.of(Rank.SECOND_PLACE, 3, 900_000_000),
				Arguments.of(Rank.THIRD_PLACE, 10, 150_000_000),
				Arguments.of(Rank.FOURTH_PLACE, 15, 750_000),
				Arguments.of(Rank.FIFTH_PLACE, 100, 500_000),
				Arguments.of(Rank.NONE, 1_000, 0));
	}

	@ParameterizedTest
	@MethodSource("generateIsWinningInput")
	void isWinning(Rank given, boolean expected) {
		// when
		boolean result = given.isWinning();

		// then
		Assertions.assertThat(result).isEqualTo(expected);
	}

	static Stream<Arguments> generateIsWinningInput() {
		return Stream.of(Arguments.of(Rank.FIRST_PLACE, true),
				Arguments.of(Rank.SECOND_PLACE, true),
				Arguments.of(Rank.THIRD_PLACE, true),
				Arguments.of(Rank.FOURTH_PLACE, true),
				Arguments.of(Rank.FIFTH_PLACE, true),
				Arguments.of(Rank.NONE, false));
	}
}