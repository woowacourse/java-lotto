package lotto.domain.lottoNumber;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberFactoryTest {
	@ParameterizedTest
	@ValueSource(ints = {1, 45})
	void generate_ValidNumber_ReturnLottoNumberInstanceFromCache(int value) {
		assertThat(LottoNumberFactory.generate(value)).isInstanceOf(LottoNumber.class);
	}

	@Test
	void generate_ValidNumbers_ReturnListOfLottoNumbers() {
		int[] numbers = {1, 2, 3, 4, 5, 6};

		List<LottoNumber> actual = LottoNumberFactory.generate(numbers);

		List<LottoNumber> expected = Arrays.asList(
			LottoNumber.valueOf(1),
			LottoNumber.valueOf(2),
			LottoNumber.valueOf(3),
			LottoNumber.valueOf(4),
			LottoNumber.valueOf(5),
			LottoNumber.valueOf(6)
		);
		assertThat(actual).isEqualTo(expected);
	}

	@ParameterizedTest
	@ValueSource(ints = {0, 46})
	void generate_InvalidLottoNumber_InvalidLottoNumberExceptionThrown(int value) {
		assertThatThrownBy(() -> LottoNumberFactory.generate(value))
			.isInstanceOf(InvalidLottoNumberException.class)
			.hasMessage(InvalidLottoNumberException.OUT_OF_BOUND_LOTTO_NUMBER);
	}
}
