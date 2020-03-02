package lotto.domain.ticket;

import lotto.domain.number.SerialLottoNumber;
import lotto.domain.number.SerialLottoNumberFactory;
import lotto.domain.random.RandomGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTicketsFactory {
	public static LottoTickets of(List<String> lottoTicketsInput) {
		List<SerialLottoNumber> lottoTickets = lottoTicketsInput.stream()
				.map(SerialLottoNumberFactory::of)
				.collect(Collectors.toUnmodifiableList());

		return new LottoTickets(lottoTickets);
	}

	public static LottoTickets of(int autoTicketCount, RandomGenerator randomGenerator) {
		List<SerialLottoNumber> lottoTickets = new ArrayList<>();
		for (int i = 0; i < autoTicketCount; i++) {
			lottoTickets.add(SerialLottoNumberFactory.of(randomGenerator));
		}

		return new LottoTickets(lottoTickets);
	}
}
