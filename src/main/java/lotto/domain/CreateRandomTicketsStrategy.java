package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CreateRandomTicketsStrategy implements CreateNumbersStrategy {
	private static final int LOTTO_NUMBER_SIZE = 6;

	@Override
	public LottoTickets create(PurchasingAmount purchasingAmount) {
		List<LottoTicket> lottoTickets = new ArrayList<>();
		purchasingAmount.forEachRemaining(count -> createLottoTicket(lottoTickets));
		return new LottoTickets(lottoTickets);
	}

	private void createLottoTicket(List<LottoTicket> lottoTickets) {
		final List<LottoNumber> randomNumbers = LottoNumberRepository.shuffledLottoNumbers();
		lottoTickets.add(new LottoTicket(
			randomNumbers.stream()
				.limit(LOTTO_NUMBER_SIZE)
				.collect(Collectors.toList())
		));
	}
}
