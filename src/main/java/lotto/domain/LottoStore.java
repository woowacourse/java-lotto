package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoStore {
	public static List<Lotto> purchaseLotto(int numberOfLotto) {
		List<Lotto> lottos = new ArrayList<>();
		for (int i = 0; i < numberOfLotto; i++) {
			lottos.add(LottoGenerator.generate());
		}
		return lottos;
	}
}
