package lotto.dto;

import java.util.Map;

import lotto.domain.LottoResult;
import lotto.domain.WinningPrize;

public class LottoResultDto {
	private Map<WinningPrize, Integer> winnerCountMapper;
	private long earningRate;

	private LottoResultDto(Map<WinningPrize, Integer> winnerCountMapper, long earningRate) {
		this.winnerCountMapper = winnerCountMapper;
		this.earningRate = earningRate;
	}

	public static LottoResultDto from(LottoResult lottoResult) {
		return new LottoResultDto(lottoResult.getWinnerCountMapper(), lottoResult.getEarningRate());
	}

	public Map<WinningPrize, Integer> getWinnerCountMapper() {
		return winnerCountMapper;
	}

	public long getEarningRate() {
		return earningRate;
	}
}
