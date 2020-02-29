package lotto.domain;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.stream.Collectors;

public class ManualTicketsFactory {
	public static LottoTickets create(List<String> values) {
		return values.stream()
			.map(LottoTicket::of)
			.collect(Collectors.collectingAndThen(toList(), LottoTickets::new));
	}
}
