package domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TicketTest {

	@DisplayName("볼들이 생성되는지 테스트")
	@Test
	void constructorTest() {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

		assertDoesNotThrow(() -> new Ticket(numbers));
	}

	@DisplayName("1등일 때 랭크 값 반환 테스트")
	@Test
	void getFistRank() {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
		Ticket ticket = new Ticket(numbers);

		List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
		int bonusBall = 7;
		WinningNumber winningNumber = new WinningNumber(winningNumbers, bonusBall);

		assertThat(ticket.getRank(winningNumber)).isEqualTo(Rank.FIRST_GRADE);
	}

	@DisplayName("2등일 때 랭크 값 반환 테스트")
	@Test
	void getSecondRank() {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 7);
		Ticket ticket = new Ticket(numbers);

		List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
		int bonusBall = 7;
		WinningNumber winningNumber = new WinningNumber(winningNumbers, bonusBall);

		assertThat(ticket.getRank(winningNumber)).isEqualTo(Rank.SECOND_GRADE);
	}

	@DisplayName("3등일 때 랭크 값 반환 테스트")
	@Test
	void getThirdRank() {
		List<Integer> numbers = Arrays.asList(2, 1, 3, 4, 5, 8);
		Ticket ticket = new Ticket(numbers);

		List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
		int bonusBall = 7;
		WinningNumber winningNumber = new WinningNumber(winningNumbers, bonusBall);

		assertThat(ticket.getRank(winningNumber)).isEqualTo(Rank.THIRD_GRADE);
	}

	@DisplayName("Ticket 불변 객체 테스트")
	@Test
	void TicketImmutable() {
		List<Integer> numbers = new ArrayList<>();
		numbers.add(1);
		numbers.add(2);
		numbers.add(3);
		numbers.add(4);
		numbers.add(5);
		numbers.add(6);

		Ticket ticket = new Ticket(numbers);

		numbers.add(7);
		numbers.add(8);

		assertThat(numbers.size()).isEqualTo(ticket.getBalls().size() + 2);
	}

	@DisplayName("Ticket getter()로 꺼냈을 때 불변이 지켜지는지 테스트")
	@Test
	void ticketGetterImmutable() {
		List<Integer> numbers = new ArrayList<>();
		numbers.add(1);
		numbers.add(2);
		numbers.add(3);
		numbers.add(4);
		numbers.add(5);
		numbers.add(6);

		Ticket ticket = new Ticket(numbers);
		List<Ball> balls = ticket.getBalls();

		assertThatThrownBy(() -> balls.add(new Ball(7)))
			.isInstanceOf(UnsupportedOperationException.class);
	}
}
