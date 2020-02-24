package lotto.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
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
}
