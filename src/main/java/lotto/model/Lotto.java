package lotto.model;

import java.util.List;
import java.util.Set;

import lotto.model.number.LottoBall;

public class Lotto {
	private static final String ERROR_DUPLICATE = "[ERROR] 한 개의 로또 내에서 숫자가 중복될 수 없습니다";
	private static final String ERROR_COUNT = "[ERROR] 로또 번호는 6개로 입력해야 합니다";

	List<LottoBall> lotto;

	public Lotto(List<LottoBall> lottoBalls) {
		this.lotto = lottoBalls;
	}

	public Lotto from(List<LottoBall> lottoBalls) {
		validate(lottoBalls);
		return new Lotto(lottoBalls);
	}

	private void validate(List<LottoBall> lottoBalls) {
		checkCount(lottoBalls);
		checkDuplicate(lottoBalls);
	}

	private void checkCount(List<LottoBall> lottoBalls) {
		if (lottoBalls.size() != 6) {
			throw new IllegalArgumentException(ERROR_COUNT);
		}
	}

	private void checkDuplicate(List<LottoBall> lottoBalls) {
		if (Set.copyOf(lottoBalls).size() != lottoBalls.size()) {
			throw new IllegalArgumentException(ERROR_DUPLICATE);
		}
	}
}
