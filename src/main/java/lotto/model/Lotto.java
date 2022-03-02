package lotto.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lotto.model.number.LottoBall;
import lotto.model.number.WinningBalls;

public class Lotto {
	private static final List<LottoBall> LOTTO_BALLS = new ArrayList<>();
	private static final int MIN_NUMBER = 1;
	private static final int MAX_NUMBER = 45;
	private static final int NUMBER_COUNT = 6;
	private static final int PRICE = 1000;
	private static final String ERROR_DUPLICATE = "[ERROR] 한 개의 로또 내에서 숫자가 중복될 수 없습니다";
	private static final String ERROR_COUNT = "[ERROR] 로또 번호는 6개로 입력해야 합니다";

	static {
		for (int number = MIN_NUMBER; number <= MAX_NUMBER; number++) {
			LOTTO_BALLS.add(LottoBall.from(String.valueOf(number)));
		}
	}

	private final List<LottoBall> autoLotto;

	public Lotto(List<LottoBall> numbers) {
		Collections.sort(numbers);
		this.autoLotto = List.copyOf(numbers);
	}

	public static List<LottoBall> generateAuto() {
		Collections.shuffle(LOTTO_BALLS);
		return LOTTO_BALLS.subList(0, NUMBER_COUNT);
	}

	public static List<LottoBall> generateManual(String[] input) {
		validate(input);
		List<LottoBall> lottoBalls = new ArrayList<>();
		for (String string : input) {
			lottoBalls.add(LottoBall.from(string));
		}
		return lottoBalls;
	}

	private static void validate(String[] input) {
		checkCount(input);
		checkDuplicate(input);
	}

	private static void checkCount(String[] input) {
		if (input.length != 6) {
			throw new IllegalArgumentException(ERROR_COUNT);
		}
	}

	private static void checkDuplicate(String[] input) {
		Set<String> distinctInput = new HashSet<>(Arrays.asList(input));
		if (distinctInput.size() < input.length) {
			throw new IllegalArgumentException(ERROR_DUPLICATE);
		}
	}

	public static int countTickets(Money money) {
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

	public List<LottoBall> getLotto() {
		return autoLotto;
	}
}
