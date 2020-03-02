package lotto.domain.ticket;

import lotto.domain.number.LottoNumber;
import lotto.domain.number.SerialLottoNumber;
import lotto.domain.number.SerialLottoNumberFactory;

import java.util.*;
import java.util.stream.Collectors;

public class AutoLottoTicketsFactory implements TicketGenerator {
	private static final int ZERO = 0;
	private static final int SIX = 6;

	private final int autoTicketsCount;
	private final List<LottoNumber> allLottoNumbers;

	public AutoLottoTicketsFactory(int autoTicketsCount) {
		this.autoTicketsCount = autoTicketsCount;
		this.allLottoNumbers = new ArrayList<>(LottoNumber.allLottoNumbers());
	}

	@Override
	public LottoTickets create() {
		List<SerialLottoNumber> lottoTickets = new ArrayList<>();

		for (int i = 0; i < autoTicketsCount; i++) {
			lottoTickets.add(createRandomLottoTicket());
		}

		return new LottoTickets(lottoTickets);
	}

	private SerialLottoNumber createRandomLottoTicket() {
		Collections.shuffle(allLottoNumbers);
		Set<LottoNumber> lottoNumbers = allLottoNumbers.subList(ZERO, SIX)
				.stream()
				.collect(Collectors.toUnmodifiableSet());

		return SerialLottoNumberFactory.of(lottoNumbers);
	}
}
