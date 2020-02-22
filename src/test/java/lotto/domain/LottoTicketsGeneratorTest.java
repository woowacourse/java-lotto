package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import lotto.domain.Ball;
import lotto.domain.LottoCount;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTicketGenerator;
import lotto.domain.LottoTickets;
import lotto.domain.LottoTicketsGenerator;

public class LottoTicketsGeneratorTest {

	@Test
	void makeLottos() {
		LottoTicketGenerator lottoTicketGenerator = () -> {
			List<Ball> balls = Arrays.asList(Ball.valueOf(1), Ball.valueOf(12), Ball.valueOf(23), Ball.valueOf(4), Ball.valueOf(5), Ball.valueOf(6));
			return new LottoTicket(balls);
		};
		LottoTicketsGenerator lottosFactory = new LottoTicketsGenerator(lottoTicketGenerator);

		LottoTickets lottoTickets = lottosFactory.createLottosByCount(LottoCount.of(5));
		assertThat(lottoTickets.size()).isEqualTo(5);

	}
}
