package lotto.domain.lottoTicket;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import lotto.domain.lottoNumber.LottoNumber;
import lotto.domain.result.WinningLotto;
import lotto.domain.result.WinningResult;

class LottoTicketsTest {
	@Test
	void produceWinningResultBy_WinningLotto_ReturnWinningResultByThisAndWinningLottoTicket() {
		LottoTickets lottoTickets = new LottoTickets(Arrays.asList(LottoTicket.valueOf("1, 2, 3, 4, 5, 6")));
		LottoTicket winningLottoTicket = LottoTicket.valueOf("2, 3, 4, 5, 6, 7");
		WinningLotto winningLotto = new WinningLotto(winningLottoTicket, LottoNumber.valueOf(1));

		assertThat(lottoTickets.produceWinningResultBy(winningLotto)).isInstanceOf(WinningResult.class);
	}
}
