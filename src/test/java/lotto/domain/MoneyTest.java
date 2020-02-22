package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import lotto.exceptions.NotAllowedMoneyAmountException;

public class MoneyTest {
	@ParameterizedTest
	@MethodSource("generateMoneyInput")
	public void initTest(int inputMoneyParsed) {
		assertThatThrownBy(() -> {
			Money money = new Money(inputMoneyParsed);
		}).isInstanceOf(NotAllowedMoneyAmountException.class)
			.hasMessageContaining("자연수");
	}

	private static Stream<Arguments> generateMoneyInput() {
		return Stream.of(
			Arguments.of(0),
			Arguments.of(-10));
	}
}