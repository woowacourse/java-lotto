package lotto.domain;

public class TotalResult {
	private final LottoResult lottoResult;
	private final LottoCount count;

	public TotalResult(LottoResult lottoResult, LottoCount count) {
		this.lottoResult = lottoResult;
		this.count = count;
	}

	public long getProfitRate() {
		return lottoResult.calculateTotalPrize() / Money.getBuyMoney(count) * 100;
	}
}
