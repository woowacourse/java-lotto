package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {

	private static final int NUMBER_OF_COUNT_TO_PICK_LOTTO = 6;
	private static final String DUPLICATE_EXCEPTION_MESSAGE = "중복된 숫자를 입력할 수 없습니다";
	private static final String WRONG_COUNT_PICK_LOTTO_EXCEPTION_MESSAGE = "로또 번호는 6개의 숫자여야 합니다";

	private final List<Number> lotto;

	public Lotto(List<Number> lotto) {
		lotto = new ArrayList<>(lotto);
		checkLottoNumber(lotto);
		this.lotto = lotto;
	}

	public static Lotto from(List<Integer> userInput) {
		return new Lotto(userInput.stream()
			.map(Number::new)
			.collect(Collectors.toList()));
	}

	private void checkLottoNumber(List<Number> lotto) {
		checkLottoNumberSize(lotto);
		checkDuplicate(lotto);
	}

	private void checkLottoNumberSize(List<Number> lotto) {
		if (lotto.size() != NUMBER_OF_COUNT_TO_PICK_LOTTO) {
			throw new IllegalArgumentException(WRONG_COUNT_PICK_LOTTO_EXCEPTION_MESSAGE);
		}
	}

	private void checkDuplicate(List<Number> lotto) {
		boolean duplicated = lotto.stream()
			.distinct()
			.count() != lotto.size();

		if (duplicated) {
			throw new IllegalArgumentException(DUPLICATE_EXCEPTION_MESSAGE);
		}
	}

	public LottoRank checkWinningResult(Lotto winningNumbers, Number bonusNumber) {
		int count = (int)lotto.stream()
			.filter(winningNumbers::isContain)
			.count();
		return LottoRank.findRank(count, lotto.contains(bonusNumber));
	}

	public boolean isContain(Number number) {
		return lotto.contains(number);
	}

	public List<Number> getLotto() {
		return Collections.unmodifiableList(lotto);
	}
}
