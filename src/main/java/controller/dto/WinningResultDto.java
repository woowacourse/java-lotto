package controller.dto;

import java.util.Map;
import java.util.stream.Collectors;

import domain.Rank;

public class WinningResultDto {

	private final Map<RankDto, Integer> ranking;
	private final double winningPercent;

	private WinningResultDto(final Map<Rank, Integer> ranking, final double rankingPercent) {
		this.ranking = ranking.keySet()
			.stream()
			.map(RankDto::fromEntity)
			.collect(Collectors.toMap(key -> key,
				key -> ranking.get(Rank.valueOf(key.getRankName()))));

		winningPercent = rankingPercent;
	}

	public static WinningResultDto fromEntity(final Map<Rank, Integer> ranking,
			final double rankingPercent) {
		return new WinningResultDto(ranking, rankingPercent);
	}

	public Map<RankDto, Integer> getRanking() {
		return ranking;
	}

	public double getWinningPercent() {
		return winningPercent;
	}
}
