package lotto.domain.ticket;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ManualLottoTicketsFactory implements LottoTicketsFactory {
	private final List<List<Integer>> manualLottoNumbers;

	public ManualLottoTicketsFactory(List<List<Integer>> manualLottoNumbers) {
		this.manualLottoNumbers = Collections.unmodifiableList(
			new ArrayList<>(Objects.requireNonNull(manualLottoNumbers)));
	}

	@Override
	public LottoTickets create() {
		return manualLottoNumbers.stream()
			.map(LottoTicket::of)
			.collect(collectingAndThen(toList(), LottoTickets::new));
	}
}
