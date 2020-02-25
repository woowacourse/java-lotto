package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoStore {
	private LottoPurchaseStrategy lottoPurchaseStrategy;

	public LottoStore(LottoPurchaseStrategy lottoPurchaseStrategy) {
		this.lottoPurchaseStrategy = lottoPurchaseStrategy;
	}

	public Lottos purchaseLotto(LottoMoney lottoMoney) {
		List<Lotto> lottos = new ArrayList<>();
		int lottoCount = lottoMoney.getPurchasedLottoCount();

		for (int i = 0; i < lottoCount; i++) {
			lottos.add(lottoPurchaseStrategy.generate());
		}
		return new Lottos(lottos);
	}
}
