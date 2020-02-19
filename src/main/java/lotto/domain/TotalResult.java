package lotto.domain;

public class TotalResult {
	public static final long LOTTO_PRICE = 1000;
	private final LottoResult lottoResult;
	private final int count;

	public TotalResult(LottoResult lottoResult, int count) {
		this.lottoResult = lottoResult;
		this.count = count;
	}

	public long getProfitRate() {
		return lottoResult.calculateTotalPrize() / (count * LOTTO_PRICE) * 100;
	}
}
