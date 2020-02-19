package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class CreateRandomTicketsStrategy implements CreateNumbersStrategy {
	private static final int MAX_NUMBER = 45;
	private static final int MIN_NUMBER = 1;
	private static final int LOTTO_NUMBER_SIZE = 6;

	private final List<LottoNumber> numberPool;

	public CreateRandomTicketsStrategy() {
		this.numberPool = createRandomNumberPool();
	}

	private List<LottoNumber> createRandomNumberPool() {
		List<LottoNumber> numberPool = new ArrayList<>();
		IntStream.rangeClosed(MIN_NUMBER, MAX_NUMBER)
			.mapToObj(LottoNumber::new)
			.forEach(numberPool::add);
		return numberPool;
	}

	@Override
	public LottoTickets create(PurchasingAmount purchasingAmount) {
		List<LottoTicket> lottoTickets = new ArrayList<>();
		List<LottoNumber> lottoNumbers = new ArrayList<>();

		purchasingAmount.forEachRemaining((count) -> {
			Collections.shuffle(numberPool);
			createLottoTicket(lottoTickets, lottoNumbers);
		});

		return new LottoTickets(lottoTickets);
	}

	private void createLottoTicket(List<LottoTicket> lottoTickets, List<LottoNumber> lottoNumbers) {
		for (int i = 0; i < LOTTO_NUMBER_SIZE; i++) {
			lottoNumbers.add(numberPool.get(i));
		}
		lottoTickets.add(new LottoTicket(lottoNumbers));
		lottoNumbers.clear();
	}
}
