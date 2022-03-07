package domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoTicket {
	private static final int NUMBER_OF_NUMBERS = 6;
	private static final int NUMBER_FOR_BONUS_CHECK = 5;
	private static final String NUMBER_OF_NUMBERS_IS_NOT_CORRECT = NUMBER_OF_NUMBERS + "개의 숫자만 허용됩니다.";
	private static final String NUMBERS_MUST_NOT_DUPLICATED = "숫자들은 중복일 수 없습니다.";

	private final Set<LottoNumber> numbers;

	private LottoTicket(Set<LottoNumber> numbers) {
		this.numbers = numbers;
	}

	public static LottoTicket byRandom() {
		List<LottoNumber> totalLottoNumber = LottoNumber.getTotalLottoNumber();
		Collections.shuffle(totalLottoNumber);
		return new LottoTicket(new HashSet<>(totalLottoNumber.subList(0, 6)));
	}

	public static LottoTicket byManual(List<Integer> inputNumbers) {
		Set<LottoNumber> numbers = inputNumbers
			.stream()
			.map(l -> LottoNumber.of(l))
			.collect(Collectors.toSet());

		validateNumbers(inputNumbers.size(), numbers.size());

		return new LottoTicket(numbers);
	}

	public ResultStatics calculate(AnswerLotto answerLotto) {
		int count = (int) numbers.stream().filter(n -> answerLotto.getNumbers().contains(n)).count();
		boolean bonus = false;

		if (count == NUMBER_FOR_BONUS_CHECK) {
			bonus = numbers.contains(answerLotto.getBonusNumber());
		}

		return ResultStatics.of(count, bonus);
	}

	public Set<LottoNumber> getNumbers() {
		return Collections.unmodifiableSet(numbers);
	}

	private static void validateNumbers(int input_size, int numbers_size) {
		validateLength(input_size);
		validateDuplication(input_size, numbers_size);
	}

	private static void validateLength(int input_size) {
		if (input_size != NUMBER_OF_NUMBERS) {
			throw new IllegalArgumentException(NUMBER_OF_NUMBERS_IS_NOT_CORRECT);
		}
	}

	private static void validateDuplication(int input_size, int numbers_size) {
		if (input_size != numbers_size) {
			throw new IllegalArgumentException(NUMBERS_MUST_NOT_DUPLICATED);
		}
	}

}
