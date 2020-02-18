package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class MoneyTest {
	@ParameterizedTest
	@MethodSource("generateMoneyInput")
	public void initTest(String moneyInput, boolean expected) {
		assertThatThrownBy(() -> {
			Money money = new Money(moneyInput);
		}).isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("자연수");
	}

	private static Stream<Arguments> generateMoneyInput() {
		return Stream.of(
			Arguments.of("a", false),
			Arguments.of("-1", false),
			Arguments.of(null, false),
			Arguments.of("", false),
			Arguments.of(" ", false));
	}
}