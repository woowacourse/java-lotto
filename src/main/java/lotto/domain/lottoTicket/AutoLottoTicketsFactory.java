package lotto.domain.lottoTicket;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lotto.domain.lottoNumber.LottoNumber;
import lotto.domain.lottoNumber.LottoNumberCache;
import lotto.domain.purchase.PurchasingCount;

public class AutoLottoTicketsFactory {

	private static final List<LottoNumber> LOTTO_NUMBERS = new ArrayList<>(LottoNumberCache.values());

	public static LottoTickets generate(PurchasingCount purchasingCount) {
		List<LottoTicket> lottoTickets = new ArrayList<>();
		purchasingCount.forEachRemaining(e -> lottoTickets.add(generate()));
		return new LottoTickets(lottoTickets);
	}

	private static LottoTicket generate() {
		Collections.shuffle(LOTTO_NUMBERS);

		return LOTTO_NUMBERS.stream()
			.limit(LottoTicket.TOTAL_SIZE)
			.collect(collectingAndThen(toList(), LottoTicket::new));
	}

}
