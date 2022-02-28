package domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoFactory {

	private static final int FIRST_LOTTO_NUMBER = 1;
	private static final int NEXT_NUMBER_OF_LAST_LOTTO_NUMBER = 46;
	private static final int LOTTO_MAX_SIZE_INDEX = 6;
	private static final int INITIAL_INDEX = 0;
	private final List<Integer> lottoNumbers = IntStream.range(FIRST_LOTTO_NUMBER, NEXT_NUMBER_OF_LAST_LOTTO_NUMBER)
		.boxed()
		.collect(Collectors.toList());

	public List<Lotto> generateLottoTicketByAuto(final Money money) {
		int autoLottoCount = money.findPurchaseLottoCount();
		return IntStream.range(INITIAL_INDEX, autoLottoCount)
			.mapToObj(index -> generateLottoByAuto())
			.collect(Collectors.toUnmodifiableList());
	}

	private Lotto generateLottoByAuto() {
		Collections.shuffle(lottoNumbers);
		return new Lotto(abstractLottoNumbersAsMuchAsLottoSize().stream()
			.map(Number::new)
			.collect(Collectors.toList()));
	}

	private List<Integer> abstractLottoNumbersAsMuchAsLottoSize() {
		return Collections.unmodifiableList(lottoNumbers.subList(INITIAL_INDEX, LOTTO_MAX_SIZE_INDEX));
	}

	public List<Lotto> generateLottoTicketByManual(String[][] inputManualLotto) {
		return Arrays.stream(inputManualLotto)
			.map(this::generateLottoByManual)
			.collect(Collectors.toList());
	}

	private Lotto generateLottoByManual(String[] manualLotto) {
		return new Lotto(Arrays.stream(manualLotto)
			.map(Number::from)
			.collect(Collectors.toList()));
	}
}
