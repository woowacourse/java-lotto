package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import lotto.exceptions.LottoNumberRangeException;

public class LottoNumberTest {
	@ParameterizedTest
	@MethodSource("generateLottoNumber")
	@DisplayName("로또 넘버 생성 잘되는 테스트")
	public void initTest(int number) {
		LottoNumber bonusNumber = new LottoNumber(number);
		assertThat(bonusNumber).isNotNull();
	}

	static Stream<Arguments> generateLottoNumber() {
		return Stream.of(
			Arguments.of(1),
			Arguments.of(45));
	}

	@ParameterizedTest
	@MethodSource("generateWrongLottoNumber")
	@DisplayName("로또 넘버 생성 잘안되는 테스트")
	public void wrongInitTest(int number) {
		assertThatThrownBy(() -> new LottoNumber(number))
			.isInstanceOf(LottoNumberRangeException.class)
			.hasMessageContaining("로또 번호");
	}

	static Stream<Arguments> generateWrongLottoNumber() {
		return Stream.of(
			Arguments.of(46),
			Arguments.of(-1));
	}
}