package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RankTest {
	@Test
	@DisplayName("로또번호와 당첨번호가 모두 일치 할때 1등 등수를 반환한다")
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
	@DisplayName("당첨번호가 5개 맞고 보너스 번호가 맞을때 2등을 반환한다")
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
	@DisplayName("당첨번호가 5개 맞고 보너스 번호가 안맞을 때 3등을 반환한다")
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
	@DisplayName("당첨번호가 4개 맞을때 4등을 반환한다")
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
	@DisplayName("당첨번호가 3개 맞을때 5등을 반환한다")
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
	@DisplayName("당첨 번호가 3개 일 때 등수 없음을 반환한다")
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
