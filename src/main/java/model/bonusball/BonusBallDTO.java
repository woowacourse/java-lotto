package model.bonusball;

import model.lottonumber.LottoNumber;

public class BonusBallDTO {
	private final LottoNumber number;

	public BonusBallDTO(LottoNumber number) {
		this.number = LottoNumber.valueOf(number);
	}

	public LottoNumber getNumber() {
		return LottoNumber.valueOf(number);
	}
}
