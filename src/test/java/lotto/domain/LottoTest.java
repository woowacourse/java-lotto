package lotto.domain;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

public class LottoTest {
	@ParameterizedTest
	@NullAndEmptySource
	void 로또가_null이나_빈값인_경우(List<Integer> value) {
		assertThatThrownBy(() -> new Lotto(value))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("null이나 빈 값");
	}

	static Stream<Arguments> generateInput_잘못된_갯수() {
		return Stream.of(Arguments.of(Arrays.asList(1, 2, 3, 4, 5)),
			Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6, 7)));
	}

	static Stream<Arguments> generateInput_당첨번호() {
		return Stream.of(Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6), 7, 6),    // 1등
			Arguments.of(Arrays.asList(1, 3, 4, 5, 6, 7), 2, 5),
			Arguments.of(Arrays.asList(1, 3, 4, 5, 6, 7), 14, 3),
			Arguments.of(Arrays.asList(1, 4, 5, 6, 7, 8), 14, 4),
			Arguments.of(Arrays.asList(1, 5, 6, 7, 8, 9), 14, 5),
			Arguments.of(Arrays.asList(1, 6, 7, 8, 9, 10), 14, 0));            // 미당첨
	}

	@Test
	void 중복_숫자() {
		assertThatThrownBy(() -> new Lotto(Arrays.asList(1, 1, 2, 3, 4, 5)))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("중복");
	}

	@ParameterizedTest
	@MethodSource("generateInput_잘못된_갯수")
	void 로또_공_갯수가_맞지_않는_경우(List<Integer> value) {
		assertThatThrownBy(() -> new Lotto(value))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("개여야 합니다");
	}

	@ParameterizedTest
	@MethodSource("generateInput_당첨번호")
	void 몇등_당첨(List<Integer> winningNumbers, int bonusNumber, int expectedPrize) {
		Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));

		assertThat(lotto.findLottoPrize(winningNumbers, bonusNumber)).isEqualTo(expectedPrize);
	}
}
