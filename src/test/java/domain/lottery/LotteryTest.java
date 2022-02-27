package domain.lottery;

import static domain.exception.LotteryExceptionMessages.*;
import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import domain.Rank;

public class LotteryTest {

	@Nested
	@DisplayName("로또 용지 하나에 포함된 숫자가")
	class CheckNumbersInLotteryTest {
		@Test
		@DisplayName("6개이면 올바른 로또이다.")
		void valid_lottery_number() {
			assertThatNoException().isThrownBy(() ->
				Lottery.from(LotteryNumberGenerator.generateLotteryNumbers(Arrays.asList(1, 2, 3, 4, 5, 6)))
			);
		}

		@Test
		@DisplayName("6개가 아니면 올바르지 않은 로또이다.")
		void invalid_lottery_number() {
			assertThatThrownBy(() ->
				Lottery.from(LotteryNumberGenerator.generateLotteryNumbers(Arrays.asList(1, 2, 3, 4, 5)))
			).isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining(INVALID_SIZE_EXCEPTION.getMessage());
		}
	}

	@Nested
	@DisplayName("로또 용지에 포함되 있는 숫자들이")
	class CheckNumberRangeTest {
		@Test
		@DisplayName("1~45 사이이면 통과")
		void valid_lottery_number_range() {
			assertThatNoException().isThrownBy(
				() -> Lottery.from(LotteryNumberGenerator.generateLotteryNumbers(Arrays.asList(1, 2, 4, 5, 6, 45))));
		}

		@Test
		@DisplayName("1~45 사이가 아니면 실패")
		void invalid_lottery_number_range() {
			assertThatThrownBy(() -> {
				Lottery.from(LotteryNumberGenerator.generateLotteryNumbers(Arrays.asList(-1, 0, 46, 3, 4, 5)));
			}).isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining(INVALID_RANGE_EXCEPTION.getMessage());
		}
	}

	@ParameterizedTest
	@MethodSource("generateLotteryAndCorrectCount")
	@DisplayName("로또 두개의 번호들이 몇개가 일치하는지 정확히 반환하면 성공")
	void correct_same_count(final List<Integer> lotteryNumbers, final int correctWinningCount) {
		//given
		final Lottery lottery = Lottery.from(
			LotteryNumberGenerator.generateLotteryNumbers(Arrays.asList(1, 2, 3, 4, 5, 6)));
		final Lottery diffLottery = Lottery.from(LotteryNumberGenerator.generateLotteryNumbers(lotteryNumbers));
		//when
		final int winningCount = lottery.countSameNumber(diffLottery);
		//then
		assertThat(winningCount).isEqualTo(correctWinningCount);
	}

	static Stream<Arguments> generateLotteryAndCorrectCount() {
		return Stream.of(
			Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6), 6),
			Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 7), 5),
			Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 8), 5),
			Arguments.of(Arrays.asList(1, 2, 3, 4, 9, 10), 4),
			Arguments.of(Arrays.asList(1, 2, 3, 9, 10, 11), 3),
			Arguments.of(Arrays.asList(1, 2, 9, 10, 11, 12), 2)
		);
	}
}