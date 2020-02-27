package lotto.domain;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ManualTicketsFactory {
	private static final String DELIMITER = ", ";

	public static LottoTickets create(List<String> values) {
		return values.stream()
			.map(ManualTicketsFactory::createLottoNumbers)
			.map(LottoTicket::new)
			.collect(Collectors.collectingAndThen(toList(), LottoTickets::new));
	}

	private static List<LottoNumber> createLottoNumbers(String values) {
		return Arrays.stream(values.split(DELIMITER))
			.mapToInt(Integer::parseInt)
			.mapToObj(LottoNumber::new)
			.collect(toList());
	}
}
