package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import lotto.exceptions.LottoNumberDuplicatedException;

public class WinningNumberTest {
	@Test
	public void initWinningNumberTest() {
		Lotto winningNumber = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
		LottoNumber bonusNumber = LottoNumber.of(7);

		assertThat(new WinningNumber(winningNumber, bonusNumber)).isNotNull();
	}

	@Test
	@DisplayName("보너스번호와 중복이있는 당첨번호 테스트")
	public void initWrongWinningNumberTest() {
		Lotto winningNumber = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
		LottoNumber duplicatedBonusNumber = LottoNumber.of(4);

		assertThatThrownBy(() -> new WinningNumber(winningNumber, duplicatedBonusNumber))
			.isInstanceOf(LottoNumberDuplicatedException.class)
			.hasMessageContaining("중복");
	}

	@ParameterizedTest
	@MethodSource("generateLottos")
	public void matchesTest(List<Integer> lottoNumber, Rank expectedRank) {
		WinningNumber winningNumber = new WinningNumber(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)), LottoNumber.of(7));
		Lotto lotto = new Lotto(lottoNumber);
		List<Rank> ranks = winningNumber.matches(new Lottos(Collections.singletonList(lotto)));
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