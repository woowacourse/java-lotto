package domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public enum Rank {
	FIFTH_GRADE(3, 5_000L),
	FOURTH_GRADE(4, 50_000L),
	THIRD_GRADE(5, 1_500_000L),
	SECOND_GRADE(5, 30_000_000L),
	FIRST_GRADE(6, 3_000_000_000L);

	public static final int CONDITION_FOR_CHECK_BONUS_BALL = 5;

	private final int matchCount;
	private final long prize;

	Rank(final int matchCount, final Long prize) {
		this.matchCount = matchCount;
		this.prize = prize;
	}

	public static List<Rank> getRanks(final Tickets tickets, final WinningNumber winningNumber) {
		return tickets.getTickets()
			.stream()
			.map(ticket -> getRank(ticket, winningNumber))
			.filter(Objects::nonNull)
			.collect(Collectors.toUnmodifiableList());
	}

	private static Rank getRank(final Ticket ticket, final WinningNumber winningNumber) {
		int matchCount = ticket.countMatch(winningNumber.getWinningBalls());

		if (matchCount == CONDITION_FOR_CHECK_BONUS_BALL) {
			return SECOND_GRADE;
		}

		return Rank.of(matchCount);
	}

	private static Rank of(final int matchCount) {
		return Arrays.stream(Rank.values())
			.filter(rank -> rank.matches(matchCount))
			.findFirst()
			.orElse(null);
	}

	private boolean matches(final int matchCount) {
		return (matchCount == this.matchCount);
	}

	public int getMatchCount() {
		return matchCount;
	}

	public long getPrize() {
		return prize;
	}

	@Override
	public String toString() {
		return "Rank{" +
			"matchCount=" + matchCount +
			", prize=" + prize +
			'}';
	}
}
