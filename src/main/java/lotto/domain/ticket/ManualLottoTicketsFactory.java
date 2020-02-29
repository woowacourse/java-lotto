package lotto.domain.ticket;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import lotto.util.StringSplitUtil;

public class ManualLottoTicketsFactory implements LottoTicketsFactory {
	private final List<String> manualLottoNumbers;

	public ManualLottoTicketsFactory(List<String> manualLottoNumbers) {
		this.manualLottoNumbers = Collections.unmodifiableList(
			new ArrayList<>(Objects.requireNonNull(manualLottoNumbers)));
	}

	@Override
	public LottoTickets create() {
		return manualLottoNumbers.stream()
			.map(StringSplitUtil::splitRawLottoNumbers)
			.map(LottoTicket::of)
			.collect(collectingAndThen(toList(), LottoTickets::new));
	}
}
