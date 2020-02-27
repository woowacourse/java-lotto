package lotto.domain;

import java.util.stream.Collectors;

public class RandomLottoTicketFactory {
	private static final int LOTTO_NUMBER_SIZE = 6;
	public LottoTickets buyRandomLottoTickets(PurchasingAmount purchasingAmount, LottoTickets lottoTickets) {
		purchasingAmount.forEachRemaining(count -> createLottoTicket(lottoTickets));
		return lottoTickets;
	}

	private void createLottoTicket(LottoTickets lottoTickets) {
		lottoTickets.add(new LottoTicket(
				LottoNumberRepository.shuffledLottoNumbers().stream()
						.limit(LOTTO_NUMBER_SIZE)
						.collect(Collectors.toList())
		));
	}

}
