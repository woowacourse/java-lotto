package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LottosFactory {
	private final LottoFactory lottoFactory;

	public LottosFactory(LottoFactory lottoFactory) {
		this.lottoFactory = lottoFactory;
	}

	public Lottos createLottosByCount(int count) {
		List<Lotto> lottos = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			lottos.add(lottoFactory.create());
		}
		return new Lottos(lottos);
	}
}
