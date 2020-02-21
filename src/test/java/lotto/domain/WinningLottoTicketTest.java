package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.domain.factory.LottoFactory;
import lotto.domain.factory.LottosFactory;

public class WinningLottoTicketTest {
	private List<Ball> balls;

	@BeforeEach
	void setup() {
		balls = Arrays.asList(Ball.of(1), Ball.of(2), Ball.of(3), Ball.of(4), Ball.of(5), Ball.of(6));
	}

	@DisplayName("당첨 번호와 보너스볼 중복시 예외")
	@Test
	void duplicateBonusBallAndLotto() {
		LottoTicket lottoTicket = new LottoTicket(balls);
		Ball ball = Ball.of(6);

		assertThatThrownBy(() -> new WinningLotto(lottoTicket, ball))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("로또 당첨 내역 랭크 반환")
	@Test
	void getResult() {
		LottoTicket lottoTicket = new LottoTicket(balls);
		Ball ball = Ball.of(40);
		WinningLotto winningLotto = new WinningLotto(lottoTicket, ball);
		LottoFactory lottoFactory = () -> new LottoTicket(balls);

		LottosFactory lottosFactory = new LottosFactory(lottoFactory);
		LottoTickets lottoTickets = lottosFactory.createLottosByCount(LottoCount.of(2));

		LottoResult result = winningLotto.getResult(lottoTickets);
		Map<LottoRank, Long> lottoResult = result.getLottoResult();
		assertThat(lottoResult)
			.extractingByKeys(LottoRank.FIFTH, LottoRank.FOURTH, LottoRank.THIRD, LottoRank.SECOND, LottoRank.FIRST)
			.containsExactly(0L, 0L, 0L, 0L, 2L);
	}
}
