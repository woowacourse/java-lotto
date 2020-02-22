package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketsTest {
	@DisplayName("로또 묶음이 입력받은 로또들 가지고 있는지 테스트")
	@Test
	void makeLottos() {
		List<Ball> balls = Arrays.asList(Ball.valueOf(1), Ball.valueOf(2), Ball.valueOf(3), Ball.valueOf(4), Ball.valueOf(5), Ball.valueOf(6));
		LottoTicket firstLottoTicket = new LottoTicket(balls);
		LottoTicket secondLottoTicket = new LottoTicket(balls);

		LottoTickets lottoTickets = new LottoTickets(Arrays.asList(firstLottoTicket, secondLottoTicket));
		assertThat(lottoTickets.getLottoTickets()).containsExactly(firstLottoTicket, secondLottoTicket);
	}

}
