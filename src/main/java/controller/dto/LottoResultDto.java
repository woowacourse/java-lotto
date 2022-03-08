package controller.dto;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import domain.Rank;

public class LottoResultDto {
	private final List<RankDto> ranks;

	public LottoResultDto(Map<Rank, Long> ranks) {
		this.ranks = toRankDto(ranks);
	}

	private List<RankDto> toRankDto(Map<Rank, Long> ranks) {
		return ranks.entrySet().stream().map(rank -> new RankDto(rank.getKey(), rank.getValue()))
			.collect(Collectors.toList());
	}

	public Map<Rank, Long> toRank() {
		Map<Rank, Long> result = new TreeMap<>();

		for (RankDto rankDto : ranks) {
			result.put(rankDto.toEntity(), rankDto.getAmount());
		}
		return result;
	}

	public List<RankDto> getRankDtos() {
		return ranks;
	}
}
