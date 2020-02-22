package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketGeneratorTest {
	@DisplayName("로또 생성기를 통해 로또 티켓이 생성되는지 테스트")
	@Test
	void createLottoTest() {
		LottoTicketGenerator lottoTicketGenerator = () -> LottoTicket.of(1, 12, 23, 4, 5, 6);
		LottoTicket lottoTicket = lottoTicketGenerator.create();
		List<LottoBall> balls = lottoTicket.getLottoBalls();

		assertThat(balls).contains(LottoBall.valueOf(1), LottoBall.valueOf(12), LottoBall.valueOf(23),
			LottoBall.valueOf(4), LottoBall.valueOf(5), LottoBall.valueOf(6));
	}

}
