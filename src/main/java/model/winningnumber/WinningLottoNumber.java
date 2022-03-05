package model.winningnumber;

import model.lottonumber.LottoNumber;
import model.lottonumber.LottoNumbers;

public class WinningLottoNumber {
	private static final String REDUPLICATION_WITH_BONUS_BALL_ERROR_MESSAGE = "[Error]: 당첨 번호와 보너스 볼이 중복됩니다.";

	private final LottoNumbers winningNumbers;
	private final LottoNumber bonusBall;

	public WinningLottoNumber(LottoNumbers winningNumbers, LottoNumber bonusBall) {
		validateReduplicationWithBonusBall(winningNumbers, bonusBall);
		this.winningNumbers = winningNumbers;
		this.bonusBall = bonusBall;
	}
	
	private void validateReduplicationWithBonusBall(LottoNumbers numbers, LottoNumber number) {
		if (numbers.checkMatchWithBonus(number)) {
			throw new IllegalArgumentException(REDUPLICATION_WITH_BONUS_BALL_ERROR_MESSAGE);
		}
	}

	public WinningLottoNumberDTO getWinningLottoNumbersDTO() {
		return new WinningLottoNumberDTO(this.winningNumbers, this.bonusBall);
	}
}
