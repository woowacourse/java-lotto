package domain;

import static constant.LottoConstant.NUMBER_FOR_BONUS_CHECK;

public enum ResultStatics {

	NOTHING(0, 0, false),
	THREE(3, 5000, false),
	FOUR(4, 50000, false),
	FIVE(5, 1500000, false),
	FIVE_AND_BONUS(5, 30000000, true),
	SIX(6, 2000000000, false);

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
			return THREE;
		}
		if (numberMatches == 4) {
			return FOUR;
		}
		if (numberMatches == NUMBER_FOR_BONUS_CHECK && !hitBonus) {
			return FIVE;
		}
		if (numberMatches == NUMBER_FOR_BONUS_CHECK && hitBonus) {
			return FIVE_AND_BONUS;
		}
		if (numberMatches == 6) {
			return SIX;
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
