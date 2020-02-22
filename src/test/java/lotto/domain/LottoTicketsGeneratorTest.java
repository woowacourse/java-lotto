package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketsGeneratorTest {
	@DisplayName("로또 묶음이 생성기를 통해 의도한 크기만큼 생성되는지 테스트")
	@Test
	void createLottosTest() {
		LottoTicketGenerator lottoTicketGenerator = () -> LottoTicket.of(1, 12, 23, 4, 5, 6);
		LottoTicketsGenerator lottosFactory = new LottoTicketsGenerator(lottoTicketGenerator);

		LottoTickets lottoTickets = lottosFactory.createLottosByCount(LottoCount.valueOf(5));
		assertThat(lottoTickets.size()).isEqualTo(5);
	}
}
