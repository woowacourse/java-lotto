package controller.dto;

import domain.Rank;

public class RankDto {

	private final int correctedBalls;
	private final long prize;
	private final String rankName;

	private RankDto(final int correctedBalls, final long prize, final String rankName) {
		this.correctedBalls = correctedBalls;
		this.prize = prize;
		this.rankName = rankName;
	}

	public static RankDto fromEntity(final Rank rank) {
		return new RankDto(rank.getCorrectedBalls(), rank.getPrize(), rank.name());
	}

	public int getCorrectedBalls() {
		return correctedBalls;
	}

	public long getPrize() {
		return prize;
	}

	public String getRankName() {
		return rankName;
	}
}
