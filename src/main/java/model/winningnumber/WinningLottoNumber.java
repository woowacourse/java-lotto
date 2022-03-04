package model.winningnumber;

import java.util.List;

import model.lottonumber.LottoNumber;
import model.lottonumber.LottoNumbers;

public class WinningLottoNumber {
	private static final String REDUPLICATION_WITH_BONUS_BALL_ERROR_MESSAGE = "[Error]: 당첨 번호와 보너스 볼이 중복됩니다.";

	private final LottoNumbers winningNumbers;
	private final LottoNumber bonusBall;

	public WinningLottoNumber(List<LottoNumber> winningNumbers, LottoNumber bonusBall) {
		validateReduplicationWithBonusBall(winningNumbers, bonusBall);
		this.winningNumbers = LottoNumbers.changeFrom(winningNumbers);
		this.bonusBall = bonusBall;
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
