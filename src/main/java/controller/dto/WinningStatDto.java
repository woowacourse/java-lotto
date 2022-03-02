package controller.dto;

import static constant.LottoConstant.*;

import java.util.Map;

import domain.LottoRank;
import domain.WinningStat;

public class WinningStatDto {

	private final Map<LottoRank, Integer> winningStat;
	private final double profit;

	private WinningStatDto(WinningStat winningStat) {
		this.winningStat = winningStat.getStat();
		this.profit = winningStat.calculateProfit(LOTTO_TICKET_PRICE);
	}

	public static WinningStatDto from(WinningStat winningStat) {
		return new WinningStatDto(winningStat);
	}

	public Map<LottoRank, Integer> getStat() {
		return winningStat;
	}

	public double getProfit() {
		return profit;
	}
}
