package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.domain.factory.LottoFactory;
import lotto.domain.factory.LottosFactory;

public class WinningLottoTicketTest {

	@DisplayName("당첨 번호와 보너스볼 중복시 예외")
	@Test
	void duplicateBonusBallAndLotto() {
		List<Ball> balls = Arrays.asList(Ball.of(1), Ball.of(2), Ball.of(3), Ball.of(4), Ball.of(5), Ball.of(6));
		LottoTicket lottoTicket = new LottoTicket(balls);
		Ball ball = Ball.of(6);

		assertThatThrownBy(() -> new WinningLotto(lottoTicket, ball))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("로또 당첨 내역 랭크 반환")
	@Test
	void getResult() {
		List<Ball> balls = Arrays.asList(Ball.of(1), Ball.of(2), Ball.of(3), Ball.of(4), Ball.of(5), Ball.of(7));
		LottoTicket lottoTicket = new LottoTicket(balls);
		Ball ball = Ball.of(40);
		WinningLotto winningLotto = new WinningLotto(lottoTicket, ball);

		LottoFactory lottoFactory = () -> new LottoTicket(balls);

		LottosFactory lottosFactory = new LottosFactory(lottoFactory);
		LottoTickets lottoTickets = lottosFactory.createLottosByCount(LottoCount.of(2));

		// TotalResult totalResult = winningLotto.getResult(lottos);
		// assertThat(totalResult.getProfitRate()).isEqualTo(200000000);
	}
}
