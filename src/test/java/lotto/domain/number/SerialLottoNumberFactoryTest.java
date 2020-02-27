package lotto.domain.number;

import lotto.domain.random.MockLottoNumberGenerator;
import lotto.exceptions.NotSixSizeException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class SerialLottoNumberFactoryTest {
	@Test
	void createByInput() {
		// given
		String input = "1,2, 3, 4,5, 6";

		// when
		SerialLottoNumber result = SerialLottoNumberFactory.of(input);

		// then
		String expected = "3,4, 5, 1, 2,6";
		Assertions.assertThat(result).isEqualTo(SerialLottoNumberFactory.of(expected));
	}

	@Test
	void createByRandom() {
		// given
		MockLottoNumberGenerator given = new MockLottoNumberGenerator();

		// when
		SerialLottoNumber result = SerialLottoNumberFactory.of(given);

		// then
		String expected = "1,2,3,4,5,6";
		Assertions.assertThat(result).isEqualTo(SerialLottoNumberFactory.of(expected));
	}

	@ParameterizedTest
	@ValueSource(strings = {"1", "1,2,3,4,5", "1,2,3,4,5,6,7", ",,,"})
	void create_NotSizeSix_ThrowException(String input) {
		// then
		Assertions.assertThatThrownBy(() -> {
			// when
			SerialLottoNumberFactory.of(input);
		}).isInstanceOf(NotSixSizeException.class)
				.hasMessageContaining("의 사이즈가 6이 아닙니다.");
	}

	@ParameterizedTest
	@ValueSource(strings = {"", "12.3,3,4", "-1,5,aa"})
	void create_InputNotInteger_ThrowException(String input) {
		// then
		Assertions.assertThatThrownBy(() -> {
			// when
			SerialLottoNumberFactory.of(input);
		}).isInstanceOf(NumberFormatException.class);
	}
}
