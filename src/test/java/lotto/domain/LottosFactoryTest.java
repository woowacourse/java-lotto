package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import lotto.domain.factory.LottoFactory;
import lotto.domain.factory.LottosFactory;

public class LottosFactoryTest {

	@Test
	void makeLottos() {
		LottoFactory lottoFactory = () -> {
			List<Ball> balls = Arrays.asList(Ball.of(1), Ball.of(12), Ball.of(23), Ball.of(4), Ball.of(5), Ball.of(6));
			return new LottoTicket(balls);
		};
		LottosFactory lottosFactory = new LottosFactory(lottoFactory);

		LottoTickets lottoTickets = lottosFactory.createLottosByCount(LottoCount.of(5));
		assertThat(lottoTickets.size()).isEqualTo(5);

	}
}
