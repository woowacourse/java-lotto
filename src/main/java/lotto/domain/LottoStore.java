package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoStore {
	private LottoPurchaseStrategy lottoPurchaseStrategy;

	public Lottos purchaseAutoLotto(int purchaseCount) {
		lottoPurchaseStrategy = new AutoLottoPurchaseStrategy();
		return purchaseLotto(purchaseCount);
	}

	public Lottos purchaseManualLotto(int purchaseCount) {
		lottoPurchaseStrategy = new ManualLottoPurchaseStrategy();
		return purchaseLotto(purchaseCount);
	}

	private Lottos purchaseLotto(int purchaseCount) {
		List<Lotto> lottos = new ArrayList<>();
		for (int i = 0; i < purchaseCount; i++) {
			lottos.add(lottoPurchaseStrategy.generate());
		}
		return new Lottos(lottos);
	}
}
