package lotto.domain;

import java.util.Arrays;

public enum Rank {
	FIFTH(0, 5000, 3),
	FOURTH(0, 50000, 4),
	THIRD(0, 1500000, 5),
	SECOND(0, 30000000, 5),
	FIRST(0, 2000000000, 6);

	private static final String MESSAGE_NOT_FOUND_RANK = "당첨되지 않았습니다.";
	private static final String RESULT_MESSAGE_FORMAT = "%d개 일치 (%d원) - %d개";
	private final long reward;
	private final int hitCount;
	private int ticketCount;

	Rank(int ticketCount, long reward, int hitCount) {
		this.ticketCount = ticketCount;
		this.reward = reward;
		this.hitCount = hitCount;
	}

	public static Rank findRank(int count, boolean bonus) {
		Rank rank = Arrays.stream(values())
				.filter(x -> x.hitCount == count)
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException(MESSAGE_NOT_FOUND_RANK));
		return rank.checkSecondOrNot(bonus);

	}

	private Rank checkSecondOrNot(boolean bonus) {
		if (this.equals(THIRD) && bonus) {
			return SECOND;
		}
		return this;
	}

	public void plusTicketCount() {
		this.ticketCount++;
	}

	public long getReward() {
		return reward;
	}

	public int getTicketCount() {
		return ticketCount;
	}

	@Override
	public String toString() {
		return String.format(RESULT_MESSAGE_FORMAT, hitCount, reward, ticketCount);
	}
}
