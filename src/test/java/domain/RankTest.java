package domain;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RankTest {
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

	@DisplayName("로또목록 등수 확인 테스트")
	@Test
	void rankTest() {
		List<Rank> actual = Rank.getRanks(tickets, winningNumber);
		List<Rank> expected = Arrays.asList(Rank.FIFTH_GRADE);

		assertThat(actual).isEqualTo(expected);
	}
}
