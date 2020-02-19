package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import lotto.controller.RankCalculator;

public class RankCalculatorTest {
	@ParameterizedTest
	@MethodSource("generateLottos")
	public void calculateTest(List<Integer> lottoNumber, Rank expectedRank) {
		WinningNumber winningNumber = new WinningNumber(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)), new BonusNumber(7));
		Lotto lotto = new Lotto(lottoNumber);
		List<Rank> ranks = RankCalculator.calculateMultiple(Collections.singletonList(lotto), winningNumber);
		assertThat(ranks).contains(expectedRank);
	}

	static Stream<Arguments> generateLottos() {
		return Stream.of(
			Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6), Rank.FIRST),
			Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 7), Rank.SECOND),
			Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 45), Rank.THIRD),
			Arguments.of(Arrays.asList(1, 2, 3, 4, 44, 45), Rank.FOURTH),
			Arguments.of(Arrays.asList(1, 2, 3, 43, 44, 45), Rank.FIFTH));
	}
}