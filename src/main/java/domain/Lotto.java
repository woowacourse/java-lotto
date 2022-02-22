package domain;

import java.util.List;

public class Lotto {

	private final List<Integer> lotto;

	public Lotto(List<Integer> lotto) {
		checkLottoNumber(lotto);
		this.lotto = lotto;
	}

	private void checkLottoNumber(List<Integer> lotto) {
		checkLottoRange(lotto);
		checkLottoNumberSize(lotto);
		checkDuplicate(lotto);
	}

	private void checkDuplicate(List<Integer> lotto) {
		boolean duplicated = lotto.stream()
			.distinct()
			.count() != lotto.size();

		if (duplicated) {
			throw new IllegalArgumentException("중복된 숫자를 입력할 수 없습니다");
		}
	}

	private void checkLottoNumberSize(List<Integer> lotto) {
		if (lotto.size() != 6) {
			throw new IllegalArgumentException("로또 번호는 6개의 숫자여야 합니다");
		}
	}

	private void checkLottoRange(List<Integer> lotto) {
		boolean isOutOfRange = lotto.stream().anyMatch(number -> number < 1 || number > 45);
		if (isOutOfRange) {
			throw new IllegalArgumentException("로또 번호는 1 ~ 45의 숫자여야 합니다");
		}
	}
}
