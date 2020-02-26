package lotto.domain;

import java.util.ArrayList;
import java.util.List;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import lotto.domain.strategy.AutoLottoPurchaseStrategy;
import lotto.domain.strategy.LottoPurchaseStrategy;
import lotto.domain.strategy.ManualLottoPurchaseStrategy;

public class LottoStore {
	private LottoPurchaseStrategy lottoPurchaseStrategy;

	public Lottos purchaseAutoLotto(int purchasedCount) {
		lottoPurchaseStrategy = new AutoLottoPurchaseStrategy();
		return purchaseLotto(purchasedCount);
	}

	public Lottos purchaseManualLotto(int purchasedCount) {
		lottoPurchaseStrategy = new ManualLottoPurchaseStrategy();
		return purchaseLotto(purchasedCount);
	}

	private Lottos purchaseLotto(int purchasedCount) {
		List<Lotto> lottos = new ArrayList<>();
		for (int i = 0; i < purchasedCount; i++) {
			lottos.add(lottoPurchaseStrategy.generate());
		}
		return new Lottos(lottos);
	}
}
