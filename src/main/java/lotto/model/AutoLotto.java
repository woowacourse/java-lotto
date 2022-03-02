package lotto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lotto.model.number.LottoBall;
import lotto.model.number.WinningBalls;

public class AutoLotto {
	private static final List<LottoBall> LOTTO_BALLS = new ArrayList<>();
	private static final int MIN_NUMBER = 1;
	private static final int MAX_NUMBER = 45;
	private static final int NUMBER_COUNT = 6;
	private static final int PRICE = 1000;

	static {
		for (int number = MIN_NUMBER; number <= MAX_NUMBER; number++) {
			LOTTO_BALLS.add(LottoBall.from(String.valueOf(number)));
		}
	}

	private final List<LottoBall> autoLotto;

	public AutoLotto(List<LottoBall> numbers) {
		Collections.sort(numbers);
		this.autoLotto = List.copyOf(numbers);
	}

	public static List<LottoBall> selectNumbers() {
		Collections.shuffle(LOTTO_BALLS);
		return LOTTO_BALLS.subList(0, NUMBER_COUNT);
	}

	// TODO : 수동 로또 개수와 나누기
	public static int countAvailableTickets(Money money) {
		return money.countAvailable(PRICE);
	}

	public int match(WinningBalls winningBalls) {
		return (int)autoLotto.stream()
				.filter(winningBalls::match)
				.count();
	}

	public boolean contains(LottoBall lottoBall) {
		return autoLotto.contains(lottoBall);
	}

	public List<LottoBall> getAutoLotto() {
		return autoLotto;
	}
}
