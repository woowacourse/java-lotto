package domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

	private final LottoGenerator lottoGenerator = new RandomLottoGenerator();
	private final List<Lotto> lottos;

	public Lottos(int count) {
		this.lottos = generateLottos(count);
	}

	private List<Lotto> generateLottos(int count) {
		List<Lotto> lottos = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			lottos.add(new Lotto(lottoGenerator));
		}
		return lottos;
	}


}
