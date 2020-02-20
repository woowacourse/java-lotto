package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class TicketComparatorTest {
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
		assertThat(TicketComparator.getMatchCount(lottoTicket, winningNumbers)).isEqualTo(6);
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

		assertThat(TicketComparator.isBonusNotMatch(lottoTicket, winningNumbers)).isFalse();
	}
}