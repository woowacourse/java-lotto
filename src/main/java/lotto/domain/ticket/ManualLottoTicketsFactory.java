package lotto.domain.ticket;

import lotto.domain.number.SerialLottoNumber;
import lotto.domain.number.SerialLottoNumberFactory;

import java.util.List;
import java.util.stream.Collectors;

public class ManualLottoTicketsFactory implements TicketGenerator {
	private final List<String> lottoTicketsInput;

	public ManualLottoTicketsFactory(final List<String> lottoTicketsInput) {
		this.lottoTicketsInput = lottoTicketsInput;
	}

	@Override
	public LottoTickets create() {
		List<SerialLottoNumber> lottoTickets = lottoTicketsInput.stream()
				.map(SerialLottoNumberFactory::of)
				.collect(Collectors.toUnmodifiableList());

		return new LottoTickets(lottoTickets);
	}
}
