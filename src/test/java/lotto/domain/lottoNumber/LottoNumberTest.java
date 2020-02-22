package lotto.domain.lottoNumber;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {
	@ParameterizedTest
	@ValueSource(ints = {1, 45})
	void LottoNumber_ValidLottoNumber_GenerateInstance(int value) {
		assertThat(LottoNumber.valueOf(value)).isInstanceOf(LottoNumber.class);
	}

	@ParameterizedTest
	@ValueSource(ints = {0, 46})
	void validate_OutOfBoundLottoNumber_InvalidLottoNumberExceptionThrown(int value) {
		assertThatThrownBy(() -> LottoNumber.valueOf(value))
			.isInstanceOf(InvalidLottoNumberException.class)
			.hasMessage(InvalidLottoNumberException.OUT_OF_BOUND_LOTTO_NUMBER);
	}

	@ParameterizedTest
	@ValueSource(strings = {"abc", "123.4"})
	void parserToInt_NotIntegerTypeNumber_InvalidLottoNumberExceptionThrown(String value) {
		assertThatThrownBy(() -> LottoNumber.valueOf(value))
			.isInstanceOf(InvalidLottoNumberException.class)
			.hasMessage(InvalidLottoNumberException.NOT_INTEGER);
	}

	@ParameterizedTest
	@ValueSource(strings = {"1", "45"})
	void valueOf_ValidLottoNumberByStringType_ReturnInstance(String value) {
		assertThat(LottoNumber.valueOf(value)).isInstanceOf(LottoNumber.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"1,2", "42,19"})
	void compareTo_LottoNumberInstance_CompareResult(int value1, int value2) {
		LottoNumber lottoNumber1 = LottoNumber.valueOf(value1);
		LottoNumber lottoNumber2 = LottoNumber.valueOf(value2);

		int actual = lottoNumber1.compareTo(lottoNumber2);

		int expected = Integer.compare(value1, value2);
		assertThat(actual).isEqualTo(expected);
	}
}
