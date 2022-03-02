package domain;

import static constant.LottoConstant.NUMBER_FOR_BONUS_CHECK;

public enum ResultStatics {

	NOTHING(0, 0, false),
	FIFTH(3, 5000, false),
	FOURTH(4, 50000, false),
	THIRD(5, 1500000, false),
	SECOND(5, 30000000, true),
	FIRST(6, 2000000000, false);

	private int numberMatches;
	private int price;
	private boolean hitBonus;

	ResultStatics(int numberMatches, int price, boolean hitBonus) {
		this.numberMatches = numberMatches;
		this.price = price;
		this.hitBonus = hitBonus;
	}

	public static ResultStatics of(int numberMatches, boolean hitBonus) {
		if (numberMatches == 3) {
			return FIFTH;
		}
		if (numberMatches == 4) {
			return FOURTH;
		}
		if (numberMatches == NUMBER_FOR_BONUS_CHECK && !hitBonus) {
			return THIRD;
		}
		if (numberMatches == NUMBER_FOR_BONUS_CHECK && hitBonus) {
			return SECOND;
		}
		if (numberMatches == 6) {
			return FIRST;
		}
		return NOTHING;
	}

	public int getNumberMatches() {
		return this.numberMatches;
	}

	public int getPrice() {
		return this.price;
	}

	public boolean isHitBonus() {
		return this.hitBonus;
	}
}
