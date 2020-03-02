package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import lotto.domain.exception.InvalidLottoBuyCountException;

public class LottoBuyCountTest {
	@ParameterizedTest
	@DisplayName("구매 개수가 0 미만인 경우 예외 테스트")
	@CsvSource(value = {"0,-1", "-1, 0", "-1, -1"})
	void validate(int manual, int auto) {
		assertThatExceptionOfType(InvalidLottoBuyCountException.class).isThrownBy(
				() -> new LottoBuyCount(manual, auto));
	}
}
