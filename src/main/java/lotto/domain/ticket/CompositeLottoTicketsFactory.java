package lotto.domain.ticket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CompositeLottoTicketsFactory implements LottoTicketsFactory {
	private final List<LottoTicketsFactory> ticketsFactories;

	public CompositeLottoTicketsFactory(List<LottoTicketsFactory> ticketsFactories) {
		this.ticketsFactories = Collections.unmodifiableList(
			new ArrayList<>(Objects.requireNonNull(ticketsFactories)));
	}

	@Override
	public LottoTickets create() {
		List<LottoTickets> result = new ArrayList<>();
		for (LottoTicketsFactory ticketsFactory : ticketsFactories) {
			result.add(ticketsFactory.create());
		}

		return result.stream()
			.flatMap(tickets -> tickets.getLottoTickets().stream())
			.collect(Collectors.collectingAndThen(Collectors.toList(), LottoTickets::new));
	}
}
