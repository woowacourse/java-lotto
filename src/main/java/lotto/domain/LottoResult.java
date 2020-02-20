package lotto.domain;

import java.util.Map;

public class LottoResult {
	private final Map<WinningType, Integer> lottoResult;

	public LottoResult(final Map<WinningType, Integer> lottoResult) {
		this.lottoResult = lottoResult;
	}

	public Map<WinningType, Integer> getLottoResult() {
		return lottoResult;
	}
}
