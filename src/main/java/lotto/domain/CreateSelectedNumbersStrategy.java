package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CreateSelectedNumbersStrategy implements CreateNumbersStrategy {
	private final LottoTickets selectedLottoTicket;

	public CreateSelectedNumbersStrategy(final Map<Integer, List<Integer>> selectedTickets) {
		List<LottoTicket> selectedLottoTickets = new ArrayList<>();

		for (int i = 0; i < selectedTickets.size(); i++) {
			List<Integer> currentNumbers = selectedTickets.get(i);
			List<LottoNumber> lottoNumbers = toLottoNumbers(currentNumbers);
			LottoTicket lottoTicket = new LottoTicket(lottoNumbers);
			selectedLottoTickets.add(lottoTicket);
		}

		selectedLottoTicket = new LottoTickets(selectedLottoTickets);
	}

	private List<LottoNumber> toLottoNumbers(List<Integer> currentNumbers) {
		return currentNumbers.stream()
		.map(LottoNumber::new)
		.collect(Collectors.toList());
	}

	@Override
	public LottoTickets create(PurchasingAmount purchasingAmount) {
		return selectedLottoTicket;
	}
}

