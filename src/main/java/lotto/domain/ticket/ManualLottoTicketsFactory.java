package lotto.domain.ticket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import lotto.util.StringUtil;

public class ManualLottoTicketsFactory implements LottoTicketsFactory {
	private final List<String> manualLottoNumbers;

	public ManualLottoTicketsFactory(List<String> manualLottoNumbers) {
		this.manualLottoNumbers = Collections.unmodifiableList(
			new ArrayList<>(Objects.requireNonNull(manualLottoNumbers)));
	}

	@Override
	public LottoTickets create() {
		return manualLottoNumbers.stream()
			.map(StringUtil::splitRawLottoNumbers)
			.map(LottoTicket::of)
			.collect(Collectors.collectingAndThen(Collectors.toList(), LottoTickets::new));
	}
}
