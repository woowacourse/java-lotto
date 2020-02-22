package lotto.domain.lottoTicket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import lotto.domain.lottoNumber.LottoNumber;

public class AutoLottoTicketsFactory {
	private static final List<LottoNumber> LOTTO_NUMBERS = new ArrayList<>(LottoNumber.values());

	public static LottoTickets generate(long numberOfLottoTicket) {
		return LongStream.range(0, numberOfLottoTicket)
			.mapToObj(i -> generate())
			.collect(Collectors.collectingAndThen(Collectors.toList(), LottoTickets::new));
	}

	private static LottoTicket generate() {
		Collections.shuffle(LOTTO_NUMBERS);

		return LOTTO_NUMBERS.stream()
			.limit(LottoTicket.TOTAL_SIZE)
			.collect(Collectors.collectingAndThen(Collectors.toList(), LottoTicket::new));
	}
}
