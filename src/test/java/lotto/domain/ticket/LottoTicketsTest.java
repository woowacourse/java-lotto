package lotto.domain.ticket;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketsTest {
	@DisplayName("로또 묶음이 입력받은 로또들 가지고 있는지 테스트")
	@Test
	void makeLottos() {
		Set<LottoBall> balls = new HashSet<>(
			Arrays.asList(LottoBall.valueOf(1), LottoBall.valueOf(2), LottoBall.valueOf(3), LottoBall.valueOf(4),
				LottoBall.valueOf(5), LottoBall.valueOf(6)));
		LottoTicket firstLottoTicket = new LottoTicket(balls);
		LottoTicket secondLottoTicket = new LottoTicket(balls);

		LottoTickets lottoTickets = new LottoTickets(Arrays.asList(firstLottoTicket, secondLottoTicket));
		assertThat(lottoTickets.getLottoTickets()).containsExactly(firstLottoTicket, secondLottoTicket);
	}
}
