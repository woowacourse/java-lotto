package lotto.domain.generator;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import lotto.domain.Ball;
import lotto.domain.LottoCount;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;

public class LottoTicketsGeneratorTest {

	@Test
	void makeLottos() {
		LottoTicketGenerator lottoTicketGenerator = () -> {
			List<Ball> balls = Arrays.asList(Ball.of(1), Ball.of(12), Ball.of(23), Ball.of(4), Ball.of(5), Ball.of(6));
			return new LottoTicket(balls);
		};
		LottoTicketsGenerator lottosFactory = new LottoTicketsGenerator(lottoTicketGenerator);

		LottoTickets lottoTickets = lottosFactory.createLottosByCount(LottoCount.of(5));
		assertThat(lottoTickets.size()).isEqualTo(5);

	}
}
