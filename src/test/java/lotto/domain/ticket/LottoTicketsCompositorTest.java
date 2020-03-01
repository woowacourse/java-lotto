package lotto.domain.ticket;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketsCompositorTest {
	@DisplayName("서로 다른 두 로또 팩토리에서 생성된 로또 조합 생성 결과 확인")
	@Test
	void composite_lottoTicket_from_different_factory_test() {
		LottoTicketsFactory factory = new TestLottoTicketsFactory(() -> LottoTicket.of(1, 2, 3, 4, 5, 6),
			LottoCount.valueOf(3));
		LottoTicketsFactory secondFactory = new TestLottoTicketsFactory(() -> LottoTicket.of(11, 12, 13, 14, 15, 16),
			LottoCount.valueOf(2));
		LottoTicketsCompositor lottoTicketsCompositor = new LottoTicketsCompositor(factory,
			secondFactory);

		LottoTickets compositedLottoTickets = lottoTicketsCompositor.composite();
		assertThat(compositedLottoTickets.getLottoTickets()).containsExactlyInAnyOrder(
			LottoTicket.of(1, 2, 3, 4, 5, 6),
			LottoTicket.of(1, 2, 3, 4, 5, 6),
			LottoTicket.of(1, 2, 3, 4, 5, 6),
			LottoTicket.of(11, 12, 13, 14, 15, 16),
			LottoTicket.of(11, 12, 13, 14, 15, 16)
		);
	}
}
