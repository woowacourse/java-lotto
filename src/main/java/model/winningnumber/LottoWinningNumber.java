package model.winningnumber;

import java.util.List;

import model.lotto.LottoNumber;

public class LottoWinningNumber {
	private static final String WINNING_NUMBER_ERROR_MESSAGE = "[Error]: 당첨 번호는 숫자여야 합니다.";
	private static final String WINNING_NUMBER_BLANK_ERROR_MESSAGE = "[Error]: 당첨 번호를 입력하세요.";
	private static final String WINNING_NUMBER_RANGE_ERROR_MESSAGE = "[Error]: 당첨 번호는 1~45 숫자여야 합니다.";
	private static final String WINNING_NUMBER_SIZE_ERROR_MESSAGE = "[Error]: 당첨 번호는 6개의 숫자여야 합니다.";
	private static final String WINNING_NUMBER_REDUPLICATION_ERROR_MESSAGE = "[Error]: 당첨 번호는 중복이 있으면 안됩니다";
	private static final String REDUPLICATION_WITH_BONUS_BALL_ERROR_MESSAGE = "[Error]: 당첨 번호와 보너스 볼이 중복됩니다.";

	private final List<Integer> winningNumbers;

	public LottoWinningNumber(List<Integer> numbers) {
		validateNumberOutOfRange(numbers);
		validateNumberSize(numbers);
		validateNumberReduplication(numbers);
		this.winningNumbers = numbers;
	}

	private void validateNumberOutOfRange(List<Integer> numbers) {
		numbers.forEach(number -> LottoNumber.validateOutOfRange(number, WINNING_NUMBER_RANGE_ERROR_MESSAGE));
	}

	private void validateNumberSize(List<Integer> numbers) {
		if (!LottoNumber.isSizeCorrect(numbers.size())) {
			throw new IllegalArgumentException(WINNING_NUMBER_SIZE_ERROR_MESSAGE);
		}
	}

	private void validateNumberReduplication(List<Integer> numbers) {
		long count = numbers.stream().distinct().count();

		if (count != numbers.size()) {
			throw new IllegalArgumentException(WINNING_NUMBER_REDUPLICATION_ERROR_MESSAGE);
		}
	}

	public void validateReduplicationWithBonusBall(String number) {
		if (winningNumbers.contains(Integer.parseInt(number))) {
			throw new IllegalArgumentException(REDUPLICATION_WITH_BONUS_BALL_ERROR_MESSAGE);
		}
	}

	public LottoWinningNumberDTO getWinningNumbersDTO() {
		return new LottoWinningNumberDTO(this.winningNumbers);
	}

}
