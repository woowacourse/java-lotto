package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoAmountTest {

	private static Stream<Arguments> createAmountAndMessage() {
		return Stream.of(
			Arguments.of(new Money(1000), 2, "수동복권이 구매금액을 초과했습니다."),
			Arguments.of(new Money(999), 0, "로또 비용이 부족합니다."),
			Arguments.of(new Money(1000), -1, "로또의 갯수는 음수가 올 수 없습니다.")
		);
	}

	@ParameterizedTest
	@MethodSource("createAmountAndMessage")
	void createByException(Money purchaseMoney, int selfLottoAmount, String message) {
		assertThatThrownBy(() -> {
			new LottoAmount(purchaseMoney, selfLottoAmount);
		}).isInstanceOf(Exception.class)
			.hasMessage(message);
	}

	@Test
	void calculateTotalLottoAmount() {
		LottoAmount lottoAmount = new LottoAmount(new Money(50000), 5);
		int expected = 50;
		assertThat(lottoAmount.calculateTotalLottoAmount()).isEqualTo(50);
	}
}