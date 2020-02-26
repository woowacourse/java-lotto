package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import lotto.domain.exception.InvalidLottoNumberException;
import lotto.domain.lotto.LottoNumber;

class LottoNumberTest {
	@DisplayName("LottoNumber 생성자에 1이상 45이하의 정수 입력이 들어올 때 객체 생성")
	@ParameterizedTest
	@ValueSource(ints = {1, 45})
	void constructor_WithinBoundNumber_CreatedNumber(int number) {
		assertThat(LottoNumber.valueOf(number))
			.isInstanceOf(LottoNumber.class);
	}

	@DisplayName("LottoNumber 생성자에 0이하 46이상의 정수 입력이 들어올 때 InvalidLottoNumberException 발생")
	@ParameterizedTest
	@ValueSource(ints = {0, 46})
	void constructor_OutOfBoundNumber_ExceptionThrown(int number) {
		assertThatThrownBy(() -> LottoNumber.valueOf(number))
			.isInstanceOf(InvalidLottoNumberException.class)
			.hasMessage(InvalidLottoNumberException.OUT_OF_BOUND);
	}

	@DisplayName("valueOf 메소드에 1이상 45이하의 정수 입력이 들어올 때 캐싱된 LottoNumber 객체를 반환")
	@Test
	void valueOf_Number_GetNumber() {
		assertThat(LottoNumber.valueOf(3) == LottoNumber.valueOf(3)).isTrue();
		assertThat(LottoNumber.valueOf(3)).extracting("number").isEqualTo(3);
	}
}
