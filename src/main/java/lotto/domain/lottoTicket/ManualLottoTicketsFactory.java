package lotto.domain.lottoTicket;

import static java.util.stream.Collectors.*;

import java.util.List;

public class ManualLottoTicketsFactory {
	public static LottoTickets generate(List<String> inputLottoTickets) {
		return inputLottoTickets.stream()
			.map(LottoTicket::valueOf)
			.collect(collectingAndThen(toList(), LottoTickets::new));
	}
}
