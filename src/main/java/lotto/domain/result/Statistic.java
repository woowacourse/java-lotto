package lotto.domain.result;

public enum Statistic {
	DEFAULT(0,0),
	THREE(3, 5000),
	FOUR(4, 50000),
	FIVE(5, 150000),
	BONUS(5, 3000000),
	SIX(6, 2000000000);

	private final int matchingNumbers;
	private final int prize;
	private int count = 0;

	Statistic(int matchingNumbers, int prize) {
		this.matchingNumbers = matchingNumbers;
		this.prize = prize;
	}

	public static Statistic getRank(int numberOfMatch) {
		for(Statistic statistic : values()){
			if(statistic.matchingNumbers == numberOfMatch){
				return statistic;
			}
		}
		return DEFAULT;
	}

	public void count() {
		this.count++;
	}
}
