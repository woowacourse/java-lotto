package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoFactory {

	private static final int FIRST_LOTTO_NUMBER = 1;
	private static final int NEXT_NUMBER_OF_LAST_LOTTO_NUMBER = 46;
	private static final int LOTTO_PRICE = 1000;
	private static final int LOTTO_MAX_SIZE_INDEX = 6;
	private static final int INITIAL_INDEX = 0;
	private static final int INITIAL_LOTTO_COUNT = 1;
	private final List<Integer> lottoNumbers = IntStream.range(FIRST_LOTTO_NUMBER, NEXT_NUMBER_OF_LAST_LOTTO_NUMBER)
		.boxed()
		.collect(Collectors.toList());

	public List<Lotto> generateLottoTicket(final Money money) {
		int lottoCount = INITIAL_LOTTO_COUNT;
		List<Lotto> lottoTicket = new ArrayList<>();

		while (money.isPossibleToPurchase(lottoCount++ * LOTTO_PRICE)) {
			lottoTicket.add(generateLotto());
		}
		return Collections.unmodifiableList(lottoTicket);
	}

	private Lotto generateLotto() {
		Collections.shuffle(lottoNumbers);
		return new Lotto(abstractLottoNumbersAsMuchAsLottoSize().stream()
			.map(Number::new)
			.collect(Collectors.toList()));
	}

	private List<Integer> abstractLottoNumbersAsMuchAsLottoSize() {
		return Collections.unmodifiableList(lottoNumbers.subList(INITIAL_INDEX, LOTTO_MAX_SIZE_INDEX));
	}
}
