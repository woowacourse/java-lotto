package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;

public class CreateRandomLottoTicket implements CreateLottoTicketStrategy {
	private static final int LOTTO_NUMBER_SIZE = 6;

	@Override
	public List<LottoTicket> buyLottoTickets(PurchasingAmount purchasingAmount, List<LottoTicket> randomLottoTickets) {
		purchasingAmount.forEachRemaining(count -> createLottoTicket(randomLottoTickets));
		return randomLottoTickets;
	}

	private void createLottoTicket(List<LottoTicket> lottoTickets) {
		lottoTickets.add(new LottoTicket(
				LottoNumberRepository.shuffledLottoNumbers().stream()
						.limit(LOTTO_NUMBER_SIZE)
						.collect(Collectors.toList())
		));
	}
}
