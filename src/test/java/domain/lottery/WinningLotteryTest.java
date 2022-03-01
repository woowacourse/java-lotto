package domain.lottery;

import static domain.exception.LotteryExceptionMessages.*;
import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import domain.Rank;

public class WinningLotteryTest {

	private Lottery winningNumbers;
	private final LotteryGenerator lotteryGenerator = new LotteryGenerator();

	@BeforeEach
	void init() {
		winningNumbers = lotteryGenerator.generateLottery(Arrays.asList(1, 2, 3, 4, 5, 6));
	}

	@Nested
	@DisplayName("보너스 볼의")
	class BonusBallTest {
		@Test
		@DisplayName("범위가 1~45 이면서 당첨번호와 중복이 없으면 통과")
		void theNumberOfBonusBall() {
			winningNumbers = lotteryGenerator.generateLottery(Arrays.asList(1, 2, 3, 4, 5, 6));
			assertThatNoException().isThrownBy(() ->
				WinningLottery.of(winningNumbers, new LotteryNumber(10))
			);
		}

		@DisplayName("범위가 1~45가 아니면 실패")
		@ParameterizedTest(name = "{index} {displayName} bonusBall={0}")
		@ValueSource(ints = {0, 46})
		void invalidBonusBallRange(final int bonusBall) {
			assertThatThrownBy(() ->
				WinningLottery.of(winningNumbers, new LotteryNumber(bonusBall))
			).isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining(INVALID_RANGE_EXCEPTION.getMessage());
		}

		@Test
		@DisplayName("중복이 있으면 실패")
		void duplicatedBonusBallNumber() {
			assertThatThrownBy(() ->
				WinningLottery.of(winningNumbers, new LotteryNumber(1))
			).isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining(DUPLICATE_NUMBER_EXCEPTION.getMessage());
		}
	}

	@DisplayName("당첨번호, 보너스와 입력된 로또 번호를 비교해 올바른 등수를 확인")
	@ParameterizedTest(name = "{index} {displayName} rank={0}")
	@MethodSource("generateParameter")
	void checkRank(final List<Integer> lottoNumbers, final Rank rank) {
		//given
		final Lottery lottery = lotteryGenerator.generateLottery(lottoNumbers);
		//when
		WinningLottery winningLottery = WinningLottery.of(winningNumbers, new LotteryNumber(7));
		//then
		assertThat(winningLottery.getRank(lottery)).isEqualTo(rank);
	}

	static Stream<Arguments> generateParameter() {
		return Stream.of(
			Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6), Rank.FIRST),
			Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 7), Rank.SECOND),
			Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 8), Rank.THIRD),
			Arguments.of(Arrays.asList(1, 2, 3, 4, 9, 10), Rank.FOURTH),
			Arguments.of(Arrays.asList(1, 2, 3, 9, 10, 11), Rank.FIFTH),
			Arguments.of(Arrays.asList(1, 2, 9, 10, 11, 12), Rank.NONE)
		);
	}
}
