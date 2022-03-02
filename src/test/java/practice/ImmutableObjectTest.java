package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.Ball;
import domain.Ticket;
import domain.TicketCounter;
import domain.TicketMachine;
import domain.Tickets;
import domain.strategy.CustomTicketingStrategy;

public class ImmutableObjectTest {

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

	@DisplayName("Tickets 불변 객체 테스트")
	@Test
	void TicketsImmutable() {
		List<List<Integer>> numbers = new ArrayList<>();
		numbers.add(Arrays.asList(8, 21, 23, 41, 42, 43));
		numbers.add(Arrays.asList(3, 5, 11, 16, 32, 38));
		numbers.add(Arrays.asList(7, 11, 16, 35, 36, 44));
		numbers.add(Arrays.asList(1, 8, 11, 31, 41, 42));
		numbers.add(Arrays.asList(13, 14, 16, 38, 42, 45));
		numbers.add(Arrays.asList(7, 11, 30, 40, 42, 43));
		numbers.add(Arrays.asList(2, 13, 22, 32, 38, 45));
		numbers.add(Arrays.asList(23, 25, 33, 36, 39, 41));
		numbers.add(Arrays.asList(1, 3, 5, 14, 22, 45));
		numbers.add(Arrays.asList(5, 9, 38, 41, 43, 44));
		numbers.add(Arrays.asList(2, 8, 9, 18, 19, 21));
		numbers.add(Arrays.asList(13, 14, 18, 21, 23, 35));
		numbers.add(Arrays.asList(17, 21, 29, 37, 42, 45));
		numbers.add(Arrays.asList(3, 8, 27, 30, 35, 44));

		CustomTicketingStrategy customLottoGenerator = new CustomTicketingStrategy();
		customLottoGenerator.initNumbers(numbers);
		int money = 14000;
		TicketCounter manualCount = new TicketCounter(money, 3);

		List<List<Integer>> manualTickets = new ArrayList<>();
		manualTickets.add(Arrays.asList(8, 21, 23, 41, 42, 43));
		manualTickets.add(Arrays.asList(3, 5, 11, 16, 32, 38));
		manualTickets.add(Arrays.asList(7, 11, 16, 35, 36, 44));

		Tickets tickets = TicketMachine.generateTickets(manualCount, manualTickets, customLottoGenerator);

		numbers.add(Arrays.asList(7, 12, 26, 36, 44, 45));

		assertThat(numbers.size()).isEqualTo(tickets.getTickets().size() + 1);
	}
}
