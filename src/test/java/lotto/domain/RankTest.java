package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class RankTest {
	@Test
	void ofTestWhenAllMatches() {
		List<LottoNumber> lottoNumbers =
			Arrays.asList(
				new LottoNumber(1),
				new LottoNumber(2),
				new LottoNumber(3),
				new LottoNumber(4),
				new LottoNumber(5),
				new LottoNumber(6)
			);

		LottoTicket lottoTicket = new LottoTicket(lottoNumbers);
		WinningNumbers winningNumbers = new WinningNumbers("1, 2, 3, 4, 5, 6", "7");

		Rank expected = Rank.FIRST;
		Rank actual = Rank.of(lottoTicket, winningNumbers);

		assertThat(actual).isEqualTo(expected);
	}

	@Test
	void ofTestWhenSecondPrize() {
		List<LottoNumber> lottoNumbers =
			Arrays.asList(
				new LottoNumber(1),
				new LottoNumber(2),
				new LottoNumber(3),
				new LottoNumber(4),
				new LottoNumber(5),
				new LottoNumber(6)
			);

		LottoTicket lottoTicket = new LottoTicket(lottoNumbers);
		WinningNumbers winningNumbers = new WinningNumbers("1, 2, 3, 4, 5, 7", "6");

		Rank expected = Rank.SECOND;
		Rank actual = Rank.of(lottoTicket, winningNumbers);

		assertThat(actual).isEqualTo(expected);
	}

	@Test
	void ofTestWhenThirdPrize() {
		List<LottoNumber> lottoNumbers =
			Arrays.asList(
				new LottoNumber(1),
				new LottoNumber(2),
				new LottoNumber(3),
				new LottoNumber(4),
				new LottoNumber(5),
				new LottoNumber(6)
			);

		LottoTicket lottoTicket = new LottoTicket(lottoNumbers);
		WinningNumbers winningNumbers = new WinningNumbers("1, 2, 3, 4, 5, 7", "8");

		Rank expected = Rank.THIRD;
		Rank actual = Rank.of(lottoTicket, winningNumbers);

		assertThat(actual).isEqualTo(expected);
	}

	@Test
	void ofTestWhenFourthPrize() {
		List<LottoNumber> lottoNumbers =
			Arrays.asList(
				new LottoNumber(1),
				new LottoNumber(2),
				new LottoNumber(3),
				new LottoNumber(4),
				new LottoNumber(5),
				new LottoNumber(6)
			);

		LottoTicket lottoTicket = new LottoTicket(lottoNumbers);
		WinningNumbers winningNumbers = new WinningNumbers("1, 2, 3, 4, 8, 9", "7");

		Rank expected = Rank.FOURTH;
		Rank actual = Rank.of(lottoTicket, winningNumbers);

		assertThat(actual).isEqualTo(expected);
	}

	@Test
	void ofTestWhenFifthPrize() {
		List<LottoNumber> lottoNumbers =
			Arrays.asList(
				new LottoNumber(1),
				new LottoNumber(2),
				new LottoNumber(3),
				new LottoNumber(4),
				new LottoNumber(5),
				new LottoNumber(6)
			);

		LottoTicket lottoTicket = new LottoTicket(lottoNumbers);
		WinningNumbers winningNumbers = new WinningNumbers("1, 2, 3, 8, 9, 10", "7");

		Rank expected = Rank.FIFTH;
		Rank actual = Rank.of(lottoTicket, winningNumbers);

		assertThat(actual).isEqualTo(expected);
	}

	@Test
	void ofTestWhenUnderThreeMatches() {
		List<LottoNumber> lottoNumbers =
			Arrays.asList(
				new LottoNumber(1),
				new LottoNumber(2),
				new LottoNumber(3),
				new LottoNumber(4),
				new LottoNumber(5),
				new LottoNumber(6)
			);

		LottoTicket lottoTicket = new LottoTicket(lottoNumbers);
		WinningNumbers winningNumbers = new WinningNumbers("1, 2, 11, 8, 9, 10", "7");

		Rank expected = Rank.NONE;
		Rank actual = Rank.of(lottoTicket, winningNumbers);

		assertThat(actual).isEqualTo(expected);
	}
}
