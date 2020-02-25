package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class LottoTicketTest {
	@Test
	void checkGetNumbers() {
		List<LottoNumber> lottoNumbers = new ArrayList<>();
		lottoNumbers.add(new LottoNumber(1));
		lottoNumbers.add(new LottoNumber(2));
		lottoNumbers.add(new LottoNumber(13));
		lottoNumbers.add(new LottoNumber(14));
		lottoNumbers.add(new LottoNumber(21));
		lottoNumbers.add(new LottoNumber(34));

		LottoTicket lottoTicket = new LottoTicket(lottoNumbers);
		List<String> expected = Arrays.asList("1", "2", "13", "14", "21", "34");
		List<String> actual = lottoTicket.getNumbers();

		assertThat(actual).isEqualTo(expected);
	}

	@Test
	void checkGetMatchCountWhenAllMatch() {
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
		assertThat(lottoTicket.getMatchCount(winningNumbers)).isEqualTo(6);
	}

	@Test
	void checkIsBonusNotMatchWhenSecondPrizeState() {
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

		assertThat(lottoTicket.isBonusNotMatch(winningNumbers)).isFalse();
	}
}
