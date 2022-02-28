package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}
