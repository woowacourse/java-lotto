package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoGenerator {
	private static final int LOWER_BOUND = 1;
	private static final int UPPER_BOUND = 45;
	private static final int START_INDEX = 0;
	private static final int END_INDEX = 6;
	private static final String ERROR_NOT_IN_RANGE = "[ERROR] 1이상 45 이하의 숫자만 허용됩니다.";
	private static final String ERROR_NOT_CORRECT_SIZE = "[ERROR] 지난 주 당첨 번호는 반드시 6개여야 합니다.";
	private static final String ERROR_DUPLICATE_DETECT = "[ERROR] 지난 주 당첨 번호는 중복일 수 없습니다.";

	private static final List<LottoNumber> candidates = IntStream
		.range(LOWER_BOUND, UPPER_BOUND + 1)
		.mapToObj(LottoNumber::new)
		.collect(Collectors.toList());

	public static List<LottoNumber> generateRandomLottoNumbers() {
		Collections.shuffle(candidates);
		return new ArrayList<>(candidates.subList(START_INDEX, END_INDEX));
	}

	public static List<LottoNumber> generateAnswerLottoNumbers(List<Integer> userInput) {
		validateSizeSix(userInput);
		validateInRange(userInput);
		validateDuplicate(userInput);
		List<LottoNumber> lottoNumbers = new ArrayList<>();
		for (int eachNum : userInput) {
			lottoNumbers.add(new LottoNumber(eachNum));
		}
		return lottoNumbers;
	}

	private static void validateInRange(List<Integer> userInput) {
		if (userInput
			.stream()
			.anyMatch(num -> num < LOWER_BOUND || num > UPPER_BOUND)) {
			throw new IllegalArgumentException(ERROR_NOT_IN_RANGE);
		}
	}

	private static void validateSizeSix(List<Integer> userInput) {
		if (userInput.size() != 6) {
			throw new IllegalArgumentException(ERROR_NOT_CORRECT_SIZE);
		}
	}

	private static void validateDuplicate(List<Integer> userInput) {
		if (userInput.size() != Set.of(userInput).size()) {
			throw new IllegalArgumentException(ERROR_DUPLICATE_DETECT);
		}
	}
}
