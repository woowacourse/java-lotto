package lotto.domain.strategy;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import lotto.domain.lottoNumber.LottoNumber;
import lotto.domain.lottoTicket.LottoTicket;
import lotto.domain.lottoTicket.LottoTickets;
import lotto.domain.purchase.PurchasingCount;

public class AutoLottoTicketsGenerator implements LottoTicketsGenerator {

	private final List<LottoNumber> lottoNumbers;

	public AutoLottoTicketsGenerator(Collection<LottoNumber> lottoNumbers) {
		this.lottoNumbers = new ArrayList<>(lottoNumbers);
	}

	@Override
	public LottoTickets generate(PurchasingCount purchasingCount) {
		return IntStream.range(0, purchasingCount.getPurchasingCount())
			.mapToObj(e -> generate())
			.collect(collectingAndThen(toList(), LottoTickets::new));
	}

	private LottoTicket generate() {
		Collections.shuffle(lottoNumbers);

		return lottoNumbers.stream()
			.limit(LottoTicket.TOTAL_SIZE)
			.collect(collectingAndThen(toList(), LottoTicket::new));
	}

}
