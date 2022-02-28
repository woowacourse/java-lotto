package controller.dto;

import domain.Rank;

public class RankDto {

	private final int correctedBalls;
	private final int prize;
	private final String rankName;

	private RankDto(final int correctedBalls, final int prize, final String rankName) {
		this.correctedBalls = correctedBalls;
		this.prize = prize;
		this.rankName = rankName;
	}

	public static RankDto fromEntity(Rank rank) {
		return new RankDto(rank.getCorrectedBalls(), rank.getPrize(), rank.name());
	}

	public int getCorrectedBalls() {
		return correctedBalls;
	}

	public int getPrize() {
		return prize;
	}

	public String getRankName() {
		return rankName;
	}
}
