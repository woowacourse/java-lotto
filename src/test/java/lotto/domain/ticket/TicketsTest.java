package lotto.domain.ticket;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.domain.rank.Rank;
import lotto.domain.ticket.generator.CustomTicketGenerator;
import lotto.domain.ticket.generator.RandomTicketGenerator;

class TicketsTest {

	private final CustomTicketGenerator customTicketGenerator = new CustomTicketGenerator();

	@DisplayName("로또목록 생성 테스트")
	@Test
	void initTest() {
		assertDoesNotThrow(() -> new Tickets(14, new RandomTicketGenerator()));
	}

	@DisplayName("로또목록 등수 확인 테스트")
	@Test
	void getRanksTest() {
		final List<List<Integer>> numbers = new ArrayList<>();
		numbers.add(Arrays.asList(1, 2, 3, 8, 9, 10));
		numbers.add(Arrays.asList(1, 2, 3, 8, 9, 45));
		numbers.add(Arrays.asList(11, 12, 13, 14, 15, 16));
		customTicketGenerator.initNumbers(numbers);

		final Tickets tickets = new Tickets(3, customTicketGenerator);

		final List<Integer> answerNumbers = Arrays.asList(1, 2, 3, 8, 9, 10);
		final Ticket answerBalls = new Ticket(answerNumbers);
		final Ball bonusBall = new Ball(45);

		final List<Rank> actual = tickets.getRanks(answerBalls, bonusBall);
		final List<Rank> expected = Arrays.asList(Rank.FIRST_GRADE, Rank.SECOND_GRADE);

		assertThat(actual).isEqualTo(expected);
	}

}
