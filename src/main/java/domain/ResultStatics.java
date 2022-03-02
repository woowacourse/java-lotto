package domain;

import java.util.Arrays;

import static constant.LottoConstant.NUMBER_FOR_WINNER;

public enum ResultStatics {

	NOTHING(0, 0, false),
	FIFTH(3, 5000, false),
	FOURTH(4, 50000, false),
	THIRD(5, 1500000, false),
	SECOND(5, 30000000, true),
	FIRST(6, 2000000000, false);

	private final int numberMatches;
	private final int price;
	private final boolean hitBonus;

	ResultStatics(int numberMatches, int price, boolean hitBonus) {
		this.numberMatches = numberMatches;
		this.price = price;
		this.hitBonus = hitBonus;
	}

	public static ResultStatics of(int numberMatches, boolean hitBonus) {
		if (numberMatches < NUMBER_FOR_WINNER) {
			return NOTHING;
		}

		return Arrays.stream(ResultStatics.values())
			.filter(r -> ((r.getNumberMatches() == numberMatches) && (r.isHitBonus() == hitBonus)))
			.findFirst()
			.get();
	}

	public int getNumberMatches() {
		return numberMatches;
	}

	public int getPrice() {
		return price;
	}

	public boolean isHitBonus() {
		return hitBonus;
	}

}
