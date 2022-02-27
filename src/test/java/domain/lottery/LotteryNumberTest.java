package domain.lottery;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domain.exception.LotteryExceptionMessages;

public class LotteryNumberTest {

	@ParameterizedTest
	@ValueSource(ints = {1, 45})
	@DisplayName("로또 번호가 1~45 범위이면 정상적으로 생성")
	void testValidLotteryNumber(final int number) {
		assertThatNoException().isThrownBy(() ->
			new LotteryNumber(number)
		);
	}

	@ParameterizedTest
	@ValueSource(ints = {-1, 0, 46})
	@DisplayName("로또 번호가 1~45 범위가 아니면 오류 발생")
	void testInvalidLotteryNumber(final int number) {
		assertThatThrownBy(() ->
			new LotteryNumber(number)
		).isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining(LotteryExceptionMessages.INVALID_RANGE_EXCEPTION.getMessage());
	}
}
