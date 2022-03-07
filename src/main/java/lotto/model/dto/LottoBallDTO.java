package lotto.model.dto;

import lotto.model.lotto.LottoBall;

public class LottoBallDTO {

	private final int number;

	private LottoBallDTO(int number) {
		this.number = number;
	}

	public static int from(LottoBall lottoBall) {
		return lottoBall.getNumber();
	}
}
