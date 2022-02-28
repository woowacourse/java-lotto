package domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lotto {

	private static final String DUPLICATION_NUMBERS_MESSAGE = "중복된 숫자를 입력할 수 없습니다";
	private static final String NOT_LOTTO_FIXED_SIZE_MESSAGE = "로또 번호는 6개의 숫자여야 합니다";
	private static final int FIXED_LOTTO_SIZE = 6;
	private final List<Number> lotto;

	public Lotto(final List<Number> lotto) {
		checkLottoNumber(lotto);
		this.lotto = lotto;
	}

	public static Lotto from(final String[] userInput) {
		return new Lotto(Stream.of(userInput)
			.map(Number::from)
			.collect(Collectors.toList()));
	}

	private void checkLottoNumber(final List<Number> lotto) {
		checkLottoNumberSize(lotto);
		checkDuplicateLottoNumber(lotto);
	}

	private void checkLottoNumberSize(final List<Number> lotto) {
		if (lotto.size() != FIXED_LOTTO_SIZE) {
			throw new IllegalArgumentException(NOT_LOTTO_FIXED_SIZE_MESSAGE);
		}
	}

	private void checkDuplicateLottoNumber(final List<Number> lotto) {
		boolean duplicated = lotto.stream()
			.distinct()
			.count() != lotto.size();

		if (duplicated) {
			throw new IllegalArgumentException(DUPLICATION_NUMBERS_MESSAGE);
		}
	}

	public boolean isContain(final Number number) {
		return lotto.contains(number);
	}

	public LottoRank confirmWinningResult(final Lotto winningNumbers, final Number bonusNumber) {
		int count = (int)lotto.stream()
			.filter(winningNumbers::isContain)
			.count();
		return LottoRank.findRank(count, lotto.contains(bonusNumber));
	}

	public List<Number> getLotto() {
		return lotto;
	}
}
