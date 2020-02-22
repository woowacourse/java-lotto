package lotto.domain;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

public class LottoTest {
	static Stream<Arguments> generateInput_잘못된_갯수() {
		return Stream.of(Arguments.of(new LottoNumbers(Arrays.asList(new LottoNumber(1)))),
			Arguments.of(new LottoNumbers(
				Arrays.asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
					new LottoNumber(5), new LottoNumber(6), new LottoNumber(7)))));
	}

	@ParameterizedTest
	@NullSource
	void 로또번호가_null인_경우(LottoNumbers lottoNumbers) {
		assertThatThrownBy(() -> new Lotto(lottoNumbers))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("null");
	}

	@ParameterizedTest
	@MethodSource("generateInput_잘못된_갯수")
	void 로또_공_갯수가_맞지_않는_경우(LottoNumbers lottoNumbers) {
		assertThatThrownBy(() -> new Lotto(lottoNumbers))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("개여야 합니다");
	}

	static Stream<Arguments> generateInput_당첨번호() {
		return Stream.of(Arguments.of(Arrays.asList("1", "2", "3", "4", "5", "6"), 7, "1등"),
			Arguments.of(Arrays.asList("1", "3", "4", "5", "6", "7"), 2, "2등(보너스볼 일치)"),
			Arguments.of(Arrays.asList("1", "3", "4", "5", "6", "7"), 14, "3등"),
			Arguments.of(Arrays.asList("1", "4", "5", "6", "7", "8"), 12, "4등"),
			Arguments.of(Arrays.asList("1", "5", "6", "7", "8", "9"), 43, "5등"),
			Arguments.of(Arrays.asList("1", "6", "7", "8", "9", "10"), 34, "미당첨"));
	}

	@ParameterizedTest
	@MethodSource("generateInput_당첨번호")
	void 몇등_당첨(List<String> winningNumbers, int bonusNumber, String expectedPrize) {
		Lotto lotto = new Lotto(new LottoNumbers(
			Arrays.asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
				new LottoNumber(5), new LottoNumber(6))));
		WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);
		assertThat(lotto.findLottoPrize(winningLotto).getPrizeDescription()).isEqualTo(expectedPrize);
	}
}
