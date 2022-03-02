package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.dto.WinningAnalyzeDto;

public class WinningAnalyzeTest {
	private SetUpTicketsAndWinningNumber setUpTicketsAndWinningNumber;

	private WinningAnalyze winningAnalyze;
	private WinningAnalyzeDto dto;

	@BeforeEach
	void setup() {
		setUpTicketsAndWinningNumber = new SetUpTicketsAndWinningNumber();
		setUpTicketsAndWinningNumber.setUp();

		winningAnalyze
			= new WinningAnalyze(setUpTicketsAndWinningNumber.getTickets(),
			setUpTicketsAndWinningNumber.getWinningNumber());

		dto = winningAnalyze.analyze();
	}

	@DisplayName("통계 자료 테스트")
	@Test
	void analyze() {
		assertThat(dto.getAnalyzeResult().get(Rank.FIFTH_GRADE)).isEqualTo(1);
	}

	@DisplayName("수익률 계산 테스트")
	@Test
	void profitRate() {
		double profitRate = dto.getProfitRate();

		assertThat(profitRate).isEqualTo(0.35);
	}
}
