package model.winningnumber;

import java.util.ArrayList;
import java.util.List;

import model.lottonumber.LottoNumber;
import model.lottonumber.LottoNumbers;

public class WinningLottoNumberDTO {
	private final LottoNumbers winningNumbers;
	private final LottoNumber bonusBall;

	public WinningLottoNumberDTO(LottoNumbers winningNumbers, LottoNumber bonusBall) {
		this.winningNumbers = LottoNumbers.valueOf(winningNumbers);
		this.bonusBall = LottoNumber.valueOf(bonusBall);
	}

	public List<LottoNumber> getWinningNumbers() {
		return new ArrayList<>(winningNumbers.getNumbers());
	}

	public LottoNumber getBonusBall() {
		return LottoNumber.valueOf(bonusBall);
	}
}
