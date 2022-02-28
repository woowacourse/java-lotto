package controller.dto;

import java.util.Map;

import domain.LottoRank;

public class WinningStatDto {

	private final Map<LottoRank, Integer> winningStat;
	private final double profit;

	private WinningStatDto(Map<LottoRank, Integer> winningStat, double profit) {
		this.winningStat = winningStat;
		this.profit = profit;
	}

	public static WinningStatDto of(Map<LottoRank, Integer> winningStat, double profit) {
		return new WinningStatDto(winningStat, profit);
	}

	public Map<LottoRank, Integer> getStat() {
		return winningStat;
	}

	public double getProfit() {
		return profit;
	}
}
