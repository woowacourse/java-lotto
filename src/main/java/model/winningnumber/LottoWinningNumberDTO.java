package model.winningnumber;

import java.util.ArrayList;
import java.util.List;

import model.lottonumber.LottoNumber;

public class LottoWinningNumberDTO {
	private final List<LottoNumber> winningNumbers;

	public LottoWinningNumberDTO(List<LottoNumber> winningNumbers) {
		this.winningNumbers = new ArrayList<>(winningNumbers);
	}

	public List<LottoNumber> getWinningNumbers() {
		return new ArrayList<>(winningNumbers);
	}
}
