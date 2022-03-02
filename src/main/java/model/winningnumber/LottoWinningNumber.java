package model.winningnumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import model.lotto.LottoNumber;
import utils.InputValidateUtils;

public class LottoWinningNumber {
	private static final String WINNING_NUMBER_ERROR_MESSAGE = "[Error]: 당첨 번호는 숫자여야 합니다.";
	private static final String WINNING_NUMBER_BLANK_ERROR_MESSAGE = "[Error]: 당첨 번호를 입력하세요.";
	private static final String WINNING_NUMBER_RANGE_ERROR_MESSAGE = "[Error]: 당첨 번호는 1~45 숫자여야 합니다.";
	private static final String WINNING_NUMBER_SIZE_ERROR_MESSAGE = "[Error]: 당첨 번호는 6개의 숫자여야 합니다.";
	private static final String WINNING_NUMBER_REDUPLICATION_ERROR_MESSAGE = "[Error]: 당첨 번호는 중복이 있으면 안됩니다";
	private static final String REDUPLICATION_WITH_BONUS_BALL_ERROR_MESSAGE = "[Error]: 당첨 번호와 보너스 볼이 중복됩니다.";

	private final List<Integer> winningNumbers;

	public LottoWinningNumber(String numbers) {
		InputValidateUtils.inputBlank(numbers, WINNING_NUMBER_BLANK_ERROR_MESSAGE);
		InputValidateUtils.inputNumber(makeNumbersToString(numbers), WINNING_NUMBER_ERROR_MESSAGE);
		validateNumberOutOfRange(numbers);

		validateNumberSize(numbers);
		validateNumberReduplication(numbers);
		this.winningNumbers = makeWinningNumbers(split(numbers));
	}

	private List<String> split(String numbers) {
		return Arrays.asList(numbers.replace(" ", "").split(","));
	}

	private String makeNumbersToString(String numbers) {
		return String.join("", split(numbers));
	}

	private void validateNumberOutOfRange(String numbers) {
		split(numbers)
			.forEach(
				number -> LottoNumber.validateOutOfRange(Integer.parseInt(number), WINNING_NUMBER_RANGE_ERROR_MESSAGE));
	}

	private void validateNumberSize(String numbers) {
		if (!LottoNumber.isSizeCorrect(split(numbers).size())) {
			throw new IllegalArgumentException(WINNING_NUMBER_SIZE_ERROR_MESSAGE);
		}
	}

	private void validateNumberReduplication(String numbers) {
		long count = split(numbers).stream().distinct().count();

		if (count != split(numbers).size()) {
			throw new IllegalArgumentException(WINNING_NUMBER_REDUPLICATION_ERROR_MESSAGE);
		}
	}

	private List<Integer> makeWinningNumbers(List<String> numbers) {
		return numbers.stream()
			.map(Integer::parseInt)
			.collect(Collectors.toList());
	}

	public void validateReduplicationWithBonusBall(String number) {
		if (winningNumbers.contains(Integer.parseInt(number))) {
			throw new IllegalArgumentException(REDUPLICATION_WITH_BONUS_BALL_ERROR_MESSAGE);
		}
	}

	public LottoWinningNumberDTO getWinningNumbersDTO() {
		return new LottoWinningNumberDTO(this.winningNumbers);
	}

	public List<Integer> getWinningNumbers() {
		return new ArrayList<>(winningNumbers);
	}

}
