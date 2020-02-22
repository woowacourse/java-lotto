package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import lotto.domain.exception.InvalidLottoNumberException;

public class LottoNumberTest {
	@ParameterizedTest
	@DisplayName("로또 번호 범위가 정상인 경우")
	@ValueSource(ints = {1, 22, 23, 24, 45})
	void of(int actual) {
		assertThat(LottoNumber.of(actual)).isInstanceOf(LottoNumber.class);
	}

	@ParameterizedTest
	@DisplayName("로또 번호 범위에서 벗어나는 경우")
	@ValueSource(ints = {-1, 0, 46})
	@SuppressWarnings("NonAsciiCharacters")
	void of_범위에서_벗어나는_경우(int actual) {
		assertThatExceptionOfType(InvalidLottoNumberException.class).isThrownBy(() -> LottoNumber.of(actual));
	}

	@ParameterizedTest
	@DisplayName("숫자가 아닌 값을 입력한 경우")
	@ValueSource(strings = {"a", "ab", "a1", "ab1", "1a", "1ab", "1a1", "1ab1"})
	@SuppressWarnings("NonAsciiCharacters")
	void of_숫자가_아닌_값을_입력한_경우(String actual) {
		assertThatExceptionOfType(NumberFormatException.class).isThrownBy(() -> LottoNumber.of(actual));
	}
}
