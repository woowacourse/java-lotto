package model.winningnumber;

import java.util.List;

import model.lottonumber.LottoNumber;

public class WinningLottoNumber {
	private static final String REDUPLICATION_WITH_BONUS_BALL_ERROR_MESSAGE = "[Error]: 당첨 번호와 보너스 볼이 중복됩니다.";
	private static final String WINNING_NUMBER_REDUPLICATION_ERROR_MESSAGE = "[Error]: 당첨 번호는 중복이 있으면 안됩니다";

	private final List<LottoNumber> winningNumbers;
	private final LottoNumber bonusBall;

	public WinningLottoNumber(List<LottoNumber> winningNumbers, LottoNumber bonusBall) {
		LottoNumber.validateSize(winningNumbers);
		validateNumberReduplication(winningNumbers);
		validateReduplicationWithBonusBall(winningNumbers, bonusBall);
		this.winningNumbers = winningNumbers;
		this.bonusBall = bonusBall;
	}

	private void validateNumberReduplication(List<LottoNumber> numbers) {
		long count = numbers.stream().distinct().count();

		if (count != numbers.size()) {
			throw new IllegalArgumentException(WINNING_NUMBER_REDUPLICATION_ERROR_MESSAGE);
		}
	}

	private void validateReduplicationWithBonusBall(List<LottoNumber> numbers, LottoNumber number) {
		if (numbers.contains(number)) {
			throw new IllegalArgumentException(REDUPLICATION_WITH_BONUS_BALL_ERROR_MESSAGE);
		}
	}

	public WinningLottoNumberDTO getWinningLottoNumbersDTO() {
		return new WinningLottoNumberDTO(this.winningNumbers, this.bonusBall);
	}
}
