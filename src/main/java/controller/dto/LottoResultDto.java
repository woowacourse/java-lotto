package controller.dto;

import java.util.Map;

import domain.Rank;

public class LottoResultDto {
	private final Map<Rank, Long> ranks;

	public LottoResultDto(Map<Rank, Long> ranks) {
		this.ranks = ranks;
	}

	public Map<Rank, Long> getRanks() {
		return ranks;
	}
}
