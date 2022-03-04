package model.winningnumber;

import java.util.ArrayList;
import java.util.List;

import model.lottonumber.LottoNumber;

public class WinningLottoNumberDTO {
	private final List<LottoNumber> winningNumbers;
	private final LottoNumber bonusBall;

	public WinningLottoNumberDTO(List<LottoNumber> winningNumbers, LottoNumber bonusBall) {
		this.winningNumbers = new ArrayList<>(winningNumbers);
		this.bonusBall = LottoNumber.valueOf(bonusBall);
	}

	public List<LottoNumber> getWinningNumbers() {
		return new ArrayList<>(winningNumbers);
	}

	public LottoNumber getBonusBall() {
		return LottoNumber.valueOf(bonusBall);
	}
}
