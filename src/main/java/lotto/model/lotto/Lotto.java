package lotto.model.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import lotto.model.Money;

public class Lotto {
	private static final List<LottoBall> LOTTO_BALLS = new ArrayList<>();
	private static final int MIN_NUMBER = 1;
	private static final int MAX_NUMBER = 45;
	private static final int NUMBER_COUNT = 6;
	private static final int PRICE = 1000;
	private static final String ERROR_DUPLICATE = "[ERROR] 한 개의 로또 내에서 숫자가 중복될 수 없습니다";
	private static final String ERROR_COUNT = "[ERROR] 로또 번호는 6개로 입력해야 합니다";

	static {
		IntStream.rangeClosed(MIN_NUMBER, MAX_NUMBER)
			.mapToObj(number -> LottoBall.from(String.valueOf(number)))
			.forEach(LOTTO_BALLS::add);
	}

	private final List<LottoBall> lotto;

	private Lotto(List<LottoBall> numbers) {
		Collections.sort(numbers);
		this.lotto = List.copyOf(numbers);
	}

	public static Lotto fromAuto() {
		return new Lotto(generateAuto());
	}

	private static List<LottoBall> generateAuto() {
		Collections.shuffle(LOTTO_BALLS);
		return LOTTO_BALLS.subList(0, NUMBER_COUNT);
	}

	public static Lotto fromManual(List<String> input) {
		return new Lotto(generateManual(input));
	}

	private static List<LottoBall> generateManual(List<String> input) {
		validate(input);
		List<LottoBall> lottoBalls = new ArrayList<>();
		input.forEach(string -> lottoBalls.add(LottoBall.from(string)));
		return lottoBalls;
	}

	private static void validate(List<String> input) {
		checkCount(input);
		checkDuplicate(input);
	}

	private static void checkCount(List<String> input) {
		if (input.size() != 6) {
			throw new IllegalArgumentException(ERROR_COUNT);
		}
	}

	private static void checkDuplicate(List<String> input) {
		Set<String> distinctInput = new HashSet<>(input);
		if (distinctInput.size() < input.size()) {
			throw new IllegalArgumentException(ERROR_DUPLICATE);
		}
	}

	public static int countTickets(Money money) {
		return money.countAvailable(PRICE);
	}

	public int match(WinningBalls winningBalls) {
		return (int)lotto.stream()
			.filter(winningBalls::match)
			.count();
	}

	public boolean contains(LottoBall lottoBall) {
		return lotto.contains(lottoBall);
	}

	public List<LottoBall> getLotto() {
		return lotto;
	}
}
