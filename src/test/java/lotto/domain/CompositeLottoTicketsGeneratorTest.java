package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CompositeLottoTicketsGeneratorTest {
	@DisplayName("LottosTicketGenerator, Count length 일치하지 않는 경우 예외 확인")
	@Test
	void constructExceptionTest() {
		List<LottoTicketGenerator> lottoTicketGenerators = Arrays.asList(new RandomLottoTicketGenerator(),
			new RandomLottoTicketGenerator());
		List<LottoCount> lottoCounts = Arrays.asList(LottoCount.valueOf(10), LottoCount.valueOf(10),
			LottoCount.valueOf(10));

		assertThatThrownBy(() -> new CompositeLottoTicketsGenerator(lottoTicketGenerators, lottoCounts))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("명시한 생성방식과 갯수만큼 로또 생성되었는지 확")
	@Test
	void generatingLottosTest() {
		List<LottoTicketGenerator> lottoGenerators = Arrays.asList(
			() -> LottoTicket.of(1, 2, 3, 4, 5, 6),
			() -> LottoTicket.of(11, 12, 13, 14, 15, 16),
			() -> LottoTicket.of(21, 22, 23, 24, 25, 26)
		);
		List<LottoCount> lottoCounts = Arrays.asList(
			LottoCount.valueOf(1),
			LottoCount.valueOf(2),
			LottoCount.valueOf(3)
		);
		CompositeLottoTicketsGenerator lottoTicketsGenerator = new CompositeLottoTicketsGenerator(
			lottoGenerators, lottoCounts);

		LottoTickets tickets = lottoTicketsGenerator.create();
		assertThat(tickets.getLottoTickets()).containsExactly(
			LottoTicket.of(1, 2, 3, 4, 5, 6),
			LottoTicket.of(11, 12, 13, 14, 15, 16),
			LottoTicket.of(11, 12, 13, 14, 15, 16),
			LottoTicket.of(21, 22, 23, 24, 25, 26),
			LottoTicket.of(21, 22, 23, 24, 25, 26),
			LottoTicket.of(21, 22, 23, 24, 25, 26)
		);
	}
}