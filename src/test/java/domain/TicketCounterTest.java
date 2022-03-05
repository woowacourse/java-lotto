package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TicketCounterTest {

	@DisplayName("구입 금액이 1000미만이면 에러 테스트")
	@ParameterizedTest
	@ValueSource(ints = {0, 369, 999})
	void money(int money) {
		assertThatThrownBy(() -> new TicketCounter(money, 3))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("구매할 로또 수가 음수일 시 에러 테스트")
	@ParameterizedTest
	@ValueSource(ints = {-100, -5, -1})
	void negative(int count) {
		assertThatThrownBy(() -> new TicketCounter(14000, count))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("구매할 로또 수를 입력 금액 내에서 살 수 없을 시  에러 테스트")
	@ParameterizedTest
	@ValueSource(ints = {15, 16, 17})
	void over(int count) {
		assertThatThrownBy(() -> new TicketCounter(14000, count))
			.isInstanceOf(IllegalArgumentException.class);
	}
}
