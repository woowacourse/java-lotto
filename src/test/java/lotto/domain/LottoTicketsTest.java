package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketsTest {

	@DisplayName("개수를 넣었을 때 개수만큼 로또를 생성해줌")
	@Test
	void makeLottos() {
		List<Ball> balls = Arrays.asList(Ball.of(1), Ball.of(2), Ball.of(3), Ball.of(4), Ball.of(5), Ball.of(6));
		LottoTicket firstLottoTicket = new LottoTicket(balls);
		LottoTicket secondLottoTicket = new LottoTicket(balls);

		LottoTickets lottoTickets = new LottoTickets(Arrays.asList(firstLottoTicket, secondLottoTicket));
		assertThat(lottoTickets.size()).isEqualTo(2);
	}

}
