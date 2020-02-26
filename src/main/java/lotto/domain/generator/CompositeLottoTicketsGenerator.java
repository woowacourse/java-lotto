package lotto.domain.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import lotto.domain.LottoTickets;

public class CompositeLottoTicketsGenerator implements LottoTicketsGenerator {
	private final List<LottoTicketsGenerator> ticketsGenerators;

	public CompositeLottoTicketsGenerator(List<LottoTicketsGenerator> ticketsGenerators) {
		this.ticketsGenerators = Collections.unmodifiableList(new ArrayList<>(Objects.requireNonNull(ticketsGenerators)));
	}

	@Override
	public LottoTickets create() {
		List<LottoTickets> result = new ArrayList<>();
		for (LottoTicketsGenerator ticketsGenerator : ticketsGenerators) {
			result.add(ticketsGenerator.create());
		}

		return result.stream()
			.flatMap(tickets -> tickets.getLottoTickets().stream())
			.collect(Collectors.collectingAndThen(Collectors.toList(), LottoTickets::new));
	}
}
