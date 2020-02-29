package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class AutoTicketsFactory {
	public static LottoTickets create(PurchasingAmount purchasingAmount) {
		List<LottoTicket> lottoTickets = new ArrayList<>();
		purchasingAmount.forEachRemaining(count ->
			lottoTickets.add(LottoTicket.of(LottoNumberRepository.shuffledLottoNumbers()))
		);
		return new LottoTickets(lottoTickets);
	}
}
