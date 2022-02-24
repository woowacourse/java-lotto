package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoFactory {

	private static final int MIN_LOTTO_NUMBER = 1;
	private static final int MAX_LOTTO_NUMBER = 45;
	private static final List<Integer> LOTTO_NUMBERS = IntStream.rangeClosed(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
		.boxed()
		.collect(Collectors.toList());
	private static final int LOTTO_PRICE = 1000;
	private static final int MIN_PURCHASE_COUNT = 1;

	private Lotto generateLotto() {
		Collections.shuffle(LOTTO_NUMBERS);
		return new Lotto(pickLottoNumbers().stream()
			.map(Number::new)
			.collect(Collectors.toList()));
	}

	private List<Integer> pickLottoNumbers() {
		return LOTTO_NUMBERS.subList(0, 6);
	}

	public List<Lotto> generateLottoTicket(Money money) {
		int purchaseCount = MIN_PURCHASE_COUNT;
		List<Lotto> lottoTicket = new ArrayList<>();
		while (money.isPossibleToPurchase(calculatePurchasePrice(purchaseCount))) {
			lottoTicket.add(generateLotto());
			++purchaseCount;
		}
		return lottoTicket;
	}

	private int calculatePurchasePrice(int purchaseCount) {
		return purchaseCount * LOTTO_PRICE;
	}
}
