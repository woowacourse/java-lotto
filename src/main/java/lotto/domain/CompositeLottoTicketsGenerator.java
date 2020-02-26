package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CompositeLottoTicketsGenerator {
	private final List<LottoTicketGenerator> lottoTicketGenerators;
	private final List<LottoCount> lottoCounts;

	public CompositeLottoTicketsGenerator(List<LottoTicketGenerator> lottoTicketGenerators,
		List<LottoCount> lottoCounts) {
		validate(lottoTicketGenerators, lottoCounts);
		this.lottoTicketGenerators = lottoTicketGenerators;
		this.lottoCounts = lottoCounts;
	}

	private void validate(List<LottoTicketGenerator> lottoTicketGenerators, List<LottoCount> lottoCounts) {
		validateNull(lottoTicketGenerators, lottoCounts);
		validateEmpty(lottoTicketGenerators, lottoCounts);
		validateSize(lottoTicketGenerators, lottoCounts);
	}

	private void validateNull(List<LottoTicketGenerator> lottoTicketGenerators, List<LottoCount> lottoCounts) {
		if (Objects.isNull(lottoTicketGenerators) || Objects.isNull(lottoCounts)) {
			throw new NullPointerException();
		}
	}

	private void validateEmpty(List<LottoTicketGenerator> lottoTicketGenerators, List<LottoCount> lottoCounts) {
		if (lottoTicketGenerators.isEmpty() || lottoCounts.isEmpty()) {
			throw new IllegalArgumentException();
		}
	}

	private void validateSize(List<LottoTicketGenerator> lottoTicketGenerators, List<LottoCount> lottoCounts) {
		if (lottoTicketGenerators.size() != lottoCounts.size()) {
			throw new IllegalArgumentException();
		}
	}

	public LottoTickets create() {
		List<LottoTickets> tickets = new ArrayList<>();
		for (int i = 0, createSize = lottoCounts.size(); i < createSize; i++) {
			tickets.add(new LottoTicketsGenerator(lottoTicketGenerators.get(i)).createLottosByCount(lottoCounts.get(i)));
		}

		return tickets.stream()
			.flatMap(tics -> tics.getLottoTickets().stream())
			.collect(Collectors.collectingAndThen(Collectors.toList(), LottoTickets::new));
	}
}
