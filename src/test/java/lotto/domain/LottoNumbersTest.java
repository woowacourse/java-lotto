package lotto.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

@SuppressWarnings("NonAsciiCharacters")
public class LottoNumbersTest {
	@ParameterizedTest
	@NullAndEmptySource
	void 널이나_빈값(List<LottoNumber> lottoNumbers) {
		assertThatThrownBy(() -> new LottoNumbers(lottoNumbers))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("null이나 빈 값");
	}

	@Test
	void 중복_숫자() {
		assertThatThrownBy(() -> new LottoNumbers(
			Arrays.asList(new LottoNumber(1), new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
				new LottoNumber(4), new LottoNumber(5))))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("중복");
	}

	@Test
	void 번호_몇개가_같은지() {
		assertThat(new LottoNumbers(Arrays.asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
			new LottoNumber(4), new LottoNumber(5), new LottoNumber(6))).matchCount(
			new LottoNumbers(Arrays.asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
				new LottoNumber(4), new LottoNumber(5), new LottoNumber(6))))).isEqualTo(6);
	}

	static Stream<Arguments> generateInput_잘못된_갯수() {
		return Stream.of(Arguments.of(Arrays.asList(new LottoNumber(1))),
			Arguments.of(Arrays.asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
				new LottoNumber(5), new LottoNumber(6), new LottoNumber(7))));
	}

	@ParameterizedTest
	@MethodSource("generateInput_잘못된_갯수")
	void 로또_번호_갯수가_맞지_않는_경우(List<LottoNumber> lottoNumbers) {
		assertThatThrownBy(() -> new LottoNumbers(lottoNumbers))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("개여야 합니다");
	}
}
