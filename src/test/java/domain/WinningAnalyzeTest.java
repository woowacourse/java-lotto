package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningAnalyzeTest {
	private SetUpTicketsAndWinningNumber setUpTicketsAndWinningNumber;

	private WinningAnalyze winningAnalyze;
	private Tickets tickets;
	private WinningNumber winningNumber;

	@BeforeEach
	void setup() {
		winningAnalyze = new WinningAnalyze();
		setUpTicketsAndWinningNumber = new SetUpTicketsAndWinningNumber();
		setUpTicketsAndWinningNumber.setUp();

		tickets = setUpTicketsAndWinningNumber.getTickets();
		winningNumber = setUpTicketsAndWinningNumber.getWinningNumber();
	}

	@DisplayName("통계 자료 생성 테스트")
	@Test
	void analyze() {
		winningAnalyze.analyze(tickets, winningNumber);

		Map<Rank, Integer> result = winningAnalyze.getAnalyzeResult();

		assertThat(result.get(Rank.FIFTH_GRADE)).isEqualTo(1);
	}

	@DisplayName("수익률 계산 테스트")
	@Test
	void profitRate() {
		winningAnalyze.analyze(tickets, winningNumber);

		double profitRate = winningAnalyze.getProfitRate();

		assertThat(profitRate).isEqualTo(0.35714285714285715);
	}
}
