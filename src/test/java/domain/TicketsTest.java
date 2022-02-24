package domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.strategy.RandomTicketGenerator;

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
		List<List<Integer>> numbers = new ArrayList<>();
		numbers.add(Arrays.asList(1, 2, 3, 8, 9, 10));
		numbers.add(Arrays.asList(1, 2, 3, 8, 9, 45));
		numbers.add(Arrays.asList(11, 12, 13, 14, 15, 16));
		customTicketGenerator.initNumbers(numbers);

		Tickets tickets = new Tickets(3, customTicketGenerator);

		List<Integer> answerNumbers = Arrays.asList(1, 2, 3, 8, 9, 10);
		Balls answerBalls = new Balls(answerNumbers);
		Ball bonusBall = new Ball(45);

		List<Rank> actual = tickets.getRanks(answerBalls, bonusBall);
		List<Rank> expected = Arrays.asList(Rank.FIRST_GRADE, Rank.SECOND_GRADE);

		assertThat(actual).isEqualTo(expected);
	}
}
