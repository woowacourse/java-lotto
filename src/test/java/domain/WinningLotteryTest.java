package domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import utils.Parser;

public class WinningLotteryTest {

	final List<LotteryNumber> winningNumbers = Parser.toLotteryNumberList(Arrays.asList(1, 2, 3, 4, 5, 6));

	@Nested
	@DisplayName("보너스 볼의")
	class BonusBallTest {

		@Test
		@DisplayName("범위가 1~45 이면서 당첨번호와 중복이 없으면 통과")
		void theNumberOfBonusBall() {
			assertThatNoException().isThrownBy(() -> {
				new WinningLottery(winningNumbers, new LotteryNumber(10));
			});
		}

		@DisplayName("범위가 1~45가 아니면 실패")
		@ParameterizedTest(name = "{index} {displayName} bonusBall={0}")
		@ValueSource(ints = {0, 46})
		void invalidBonusBallRange(final int bonusBall) {
			assertThatThrownBy(() -> {
				new WinningLottery(winningNumbers, new LotteryNumber(bonusBall));
			}).isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("각 로또번호는 1~45 사이여야 합니다");
		}

		@Test
		@DisplayName("중복이 있으면 실패")
		void duplicatedBonusBallNumber() {
			assertThatThrownBy(() -> {
				new WinningLottery(winningNumbers, new LotteryNumber(1));
			}).isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("당첨번호와 보너스볼에 중복된 번호가 있으면 안됩니다.");
		}
	}

	@DisplayName("당첨번호, 보너스와 입력된 로또 번호를 비교해 올바른 등수를 확인")
	@ParameterizedTest(name = "{index} {displayName} rank={0}")
	@MethodSource("generateParameter")
	void checkRank(List<LotteryNumber> lottoNumbers, Rank rank) {
		WinningLottery winningLottery = new WinningLottery(winningNumbers, new LotteryNumber(7));
		Lottery lottery = new Lottery(lottoNumbers);
		assertThat(winningLottery.getRank(lottery)).isEqualTo(rank);
	}

	static Stream<Arguments> generateParameter() {
		return Stream.of(
			Arguments.of(Parser.toLotteryNumberList(Arrays.asList(1,2,3,4,5,6)), Rank.FIRST),
			Arguments.of(Parser.toLotteryNumberList(Arrays.asList(1,2,3,4,5,7)), Rank.SECOND),
			Arguments.of(Parser.toLotteryNumberList(Arrays.asList(1,2,3,4,5,8)), Rank.THIRD),
			Arguments.of(Parser.toLotteryNumberList(Arrays.asList(1,2,3,4,9,10)), Rank.FOURTH),
			Arguments.of(Parser.toLotteryNumberList(Arrays.asList(1,2,3,9,10,11)), Rank.FIFTH),
			Arguments.of(Parser.toLotteryNumberList(Arrays.asList(1,2,9,10,11,12)), Rank.NONE)
		);
	}
}
