package lotto.domain.number;

import lotto.exceptions.LottoNumberIllegalException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {
	@ParameterizedTest
	@ValueSource(ints = {1, 10, 45})
	void of(int input) {
		// when
		LottoNumber result = LottoNumber.of(input);

		// then
		Assertions.assertThat(result.getLottoNumber()).isEqualTo(input);
	}

	@ParameterizedTest
	@ValueSource(ints = {-1, 0, 46, Integer.MAX_VALUE})
	void of_OutOfLottoRange_ThrowException(int input) {
		// then
		Assertions.assertThatThrownBy(() -> {
			// when
			LottoNumber.of(input);
		}).isInstanceOf(LottoNumberIllegalException.class)
				.hasMessageMatching("-?[0-9]+는 적절하지 않은 로또 번호입니다.\n" +
						" - 1이상 45이하의 정수를 입력해주세요.");
	}
}
