package domain.generator;

import java.util.ArrayList;
import java.util.List;

import domain.Lotto;
import domain.Lottos;

public class AutoLottoGenerator implements LottoGenerator {

	public Lottos createLottos(int count, int size) {
		List<Lotto> lottos = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			lottos.add(createAutoLotto(size));
		}
		return new Lottos(lottos);
	}
}
