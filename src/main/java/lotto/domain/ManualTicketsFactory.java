package lotto.domain;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ManualTicketsFactory {
	private static final String DELIMITER = ", ";

	public static LottoTickets create(List<String> values) {
		return values.stream()
			.map(createLottoNumbers())
			.map(LottoTicket::new)
			.collect(Collectors.collectingAndThen(toList(), LottoTickets::new));
	}

	private static Function<String, List<LottoNumber>> createLottoNumbers() {
		return str -> Arrays.stream(str.split(DELIMITER))
			.mapToInt(Integer::parseInt)
			.mapToObj(LottoNumber::new)
			.collect(toList());
	}
}
