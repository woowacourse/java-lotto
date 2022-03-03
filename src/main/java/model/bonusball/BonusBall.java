package model.bonusball;

import model.lottonumber.LottoNumber;

public class BonusBall {
	private final LottoNumber number;

	public BonusBall(LottoNumber number) {
		this.number = number;
	}

	public BonusBallDTO getBonusBallDTO() {
		return new BonusBallDTO(number);
	}

}
