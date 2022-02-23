package lotto.domain.ticket;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import lotto.domain.ball.Ball;
import lotto.domain.ball.Balls;
import lotto.domain.rank.Rank;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TicketTest {

	private final CustomTicketGenerator customTicketGenerator = new CustomTicketGenerator();

	@DisplayName("로또 생성 테스트")
	@Test
	void initTest() {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
		customTicketGenerator.initNumbers(Arrays.asList(numbers));

		assertDoesNotThrow(() -> new Ticket(customTicketGenerator));
	}

	@DisplayName("당첨번호와 일치 갯수 확인 테스트")
	@Test
	void countTest() {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
		customTicketGenerator.initNumbers(Arrays.asList(numbers));

		Ticket ticket = new Ticket(customTicketGenerator);

		Balls answer = new Balls(numbers);

		assertThat(ticket.getRank(answer, new Ball(7))).isEqualTo(Rank.FIRST_GRADE);
	}
}
