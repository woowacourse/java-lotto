package lotto.domain.lottonumber;

import lotto.domain.lottonumber.InvalidLottoNumberException;
import lotto.domain.lottonumber.LottoNumber;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
				Arguments.of(1, LottoNumber.of(1)),
				Arguments.of(20, LottoNumber.of(20)),
				Arguments.of(45, LottoNumber.of(45)),
				Arguments.of(40, LottoNumber.of(40))
		);
	}

	@ParameterizedTest
	@MethodSource("getWrongTestCase")
	void of_잘못된_입력_예외처리(int inputLottoNumber) {
		assertThatThrownBy(() -> LottoNumber.of(inputLottoNumber))
				.isInstanceOf(InvalidLottoNumberException.class)
				.hasMessage("유효한 로또 번호가 아닙니다.");
	}

	private static Stream<Arguments> getWrongTestCase() {
		return Stream.of(
				Arguments.of(-99),
				Arguments.of(0),
				Arguments.of(46),
				Arguments.of(10000)
		);
	}
}
