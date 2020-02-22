package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningLottoTicketTest {
	private LottoTicket lottoTicket;

	@BeforeEach
	void setup() {
		lottoTicket = LottoTicket.of(1, 2, 3, 4, 5, 6);
	}

	@DisplayName("당첨 번호와 보너스볼 중복시 예외")
	@Test
	void duplicateBonusBallAndLotto() {
		Ball ball = Ball.valueOf(6);

		assertThatThrownBy(() -> new WinningLotto(lottoTicket, ball))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("로또 당첨 내역 랭크 반환")
	@Test
	void getResult() {
		Ball ball = Ball.valueOf(40);
		WinningLotto winningLotto = new WinningLotto(lottoTicket, ball);
		LottoTicketGenerator lottoTicketGenerator = () -> lottoTicket;

		LottoTicketsGenerator lottosFactory = new LottoTicketsGenerator(lottoTicketGenerator);
		LottoTickets lottoTickets = lottosFactory.createLottosByCount(LottoCount.valueOf(2));

		WinningResult result = winningLotto.getResult(lottoTickets);
		Map<LottoRank, Long> lottoResult = result.getWinningResult();
		assertThat(lottoResult)
			.extractingByKeys(LottoRank.FIFTH, LottoRank.FOURTH, LottoRank.THIRD, LottoRank.SECOND, LottoRank.FIRST)
			.containsExactly(0L, 0L, 0L, 0L, 2L);
	}
}
