package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoStore {
	private LottoPurchaseStrategy lottoPurchaseStrategy;

	public LottoStore(LottoPurchaseStrategy lottoPurchaseStrategy) {
		this.lottoPurchaseStrategy = lottoPurchaseStrategy;
	}

	public Lottos purchaseLotto(int numberOfLotto) {
		List<Lotto> lottos = new ArrayList<>();
		for (int i = 0; i < numberOfLotto; i++) {
			lottos.add(lottoPurchaseStrategy.generate());
		}
		return new Lottos(lottos);
	}
}
