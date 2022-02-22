package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoFactory {

	private final List<Integer> lottoNumbers = IntStream.range(1, 46)
		.boxed()
		.collect(Collectors.toList());

	private Lotto generateLotto() {
		Collections.shuffle(lottoNumbers);
		return new Lotto(this.lottoNumbers.subList(0, 6).stream()
			.map(Number::new)
			.collect(Collectors.toList()));
	}

	public List<Lotto> generateLottoByMoney(Money money) {
		int count = 1;
		List<Lotto> lottoTicket = new ArrayList<>();
		while (money.isPossibleToPurchase(count++ * 1000)) {
			lottoTicket.add(generateLotto());
		}
		return lottoTicket;
	}
}
