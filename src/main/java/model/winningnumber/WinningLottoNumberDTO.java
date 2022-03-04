package model.winningnumber;

import java.util.ArrayList;
import java.util.List;

import model.lottonumber.LottoNumber;
import model.lottonumber.LottoNumbers;
import model.lottonumber.LottoNumbersDTO;

public class WinningLottoNumberDTO {
	private final LottoNumbers winningNumbers;
	private final LottoNumber bonusBall;

	public WinningLottoNumberDTO(LottoNumbers winningNumbers, LottoNumber bonusBall) {
		this.winningNumbers = LottoNumbers.valueOf(winningNumbers);
		this.bonusBall = LottoNumber.valueOf(bonusBall);
	}

	public List<LottoNumber> getWinningNumbers() {
		LottoNumbersDTO lottoNumbersDTO = winningNumbers.getLottoNumbersDTO();
		return new ArrayList<>(lottoNumbersDTO.getLottoNumbers());
	}

	public LottoNumber getBonusBall() {
		return LottoNumber.valueOf(bonusBall);
	}
}
