package lotto.domain.ticket;

import lotto.domain.number.LottoNumber;
import lotto.domain.number.SerialLottoNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AutoLottoTicketsFactory implements TicketsGenerator {
	private static final int ZERO = 0;
	private static final int SIX = 6;

	private final int autoTicketsCount;
	private final List<LottoNumber> allLottoNumbers;

	private AutoLottoTicketsFactory(final int autoTicketsCount) {
		this.autoTicketsCount = autoTicketsCount;
		this.allLottoNumbers = new ArrayList<>(LottoNumber.allLottoNumbers());
	}

	public static AutoLottoTicketsFactory of(final int autoTicketsCount) {
		return new AutoLottoTicketsFactory(autoTicketsCount);
	}

	@Override
	public List<SerialLottoNumber> create() {
		List<SerialLottoNumber> lottoTickets = new ArrayList<>();

		for (int i = 0; i < autoTicketsCount; i++) {
			lottoTickets.add(createRandomLottoTicket());
		}

		return lottoTickets;
	}

	private SerialLottoNumber createRandomLottoTicket() {
		Collections.shuffle(allLottoNumbers);
		Set<LottoNumber> lottoNumbers = allLottoNumbers.subList(ZERO, SIX)
				.stream()
				.collect(Collectors.toUnmodifiableSet());

		return SerialLottoNumber.of(lottoNumbers);
	}
}
