package lotto.domain;

/**
 * 클래스 이름 : .java
 *
 * @author
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/19
 */
public enum Rank {
	FIRST(2000000000),
	SECOND(30000000),
	THIRD(1500000),
	FOURTH(50000),
	FIFTH(5000),
	SIXTH(0);

	private int reward;

	Rank(final int reward) {
		this.reward = reward;
	}

	public int getReward() {
		return this.reward;
	}
}
