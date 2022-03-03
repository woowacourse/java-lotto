package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.strategy.CustomTicketingStrategy;

class TicketsTest {
	private SetUpTicketsAndWinningNumber setUpTicketsAndWinningNumber;

	private Tickets tickets;
	private WinningNumber winningNumber;

	@BeforeEach
	void setUp() {
		setUpTicketsAndWinningNumber = new SetUpTicketsAndWinningNumber();
		setUpTicketsAndWinningNumber.setUp();

		tickets = setUpTicketsAndWinningNumber.getTickets();
		winningNumber = setUpTicketsAndWinningNumber.getWinningNumber();
	}

	@DisplayName("개수에 맞게 로또가 생성되는지 테스트")
	@Test
	void sizeTest() {
		assertThat(tickets.getTickets().size()).isEqualTo(14);
	}

	@DisplayName("로또목록 등수 확인 테스트")
	@Test
	void rankTest() {
		List<Rank> actual = tickets.getRanks(winningNumber);
		List<Rank> expected = Arrays.asList(Rank.FIFTH_GRADE);

		assertThat(actual).isEqualTo(expected);
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
