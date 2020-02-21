package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoTicketFactoryTest {
	@DisplayName("문자열 형식이 맞지 않는 경우 로또 발급 실패")
	@ParameterizedTest
	@ValueSource(strings = {"1, 2, 3, 4, 5, ", "1, 2, 3, 4, 5, 6.", "1, 2, 3, 4, 5"})
	void constructFailByRawNumber(String rawNumbers) {
		assertThatThrownBy(() -> LottoTicketFactory.of(rawNumbers))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("문자열로 직접 로또 발급 기능 테스트")
	@Test
	void constructByRawNumber() {
		LottoTicket lottoTicket = LottoTicketFactory.of("1, 2, 3, 4, 5, 6");
		List<Ball> balls = lottoTicket.getBalls();
		assertThat(balls).containsExactly(Ball.of(1), Ball.of(2), Ball.of(3), Ball.of(4), Ball.of(5), Ball.of(6));
	}
}