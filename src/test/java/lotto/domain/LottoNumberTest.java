package lotto.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * LottoNumber 테스트
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/18
 */
public class LottoNumberTest {
	@ParameterizedTest
	@MethodSource("getRightTestCase")
	void of_올바른_동작_확인(int inputLottoNumber, LottoNumber expected) {
		assertThat(LottoNumber.of(inputLottoNumber)).isEqualTo(expected);
	}

	private static Stream<Arguments> getRightTestCase() {
		return Stream.of(
				Arguments.of(1, LottoNumber.ONE),
				Arguments.of(20, LottoNumber.TWENTY),
				Arguments.of(45, LottoNumber.FORTY_FIVE)
		);
	}
}
