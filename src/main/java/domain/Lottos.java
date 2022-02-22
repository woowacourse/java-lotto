package domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

	private final List<Lotto> lottos = new ArrayList<>();

	public Lottos(int price) {
		int count = price / 1000;
		while (count-- > 0) {
			this.lottos.add(new Lotto(new RandomLottoNumberGenerator()));
		}
	}

	public List<Lotto> getLottos() {
		return lottos;
	}

	public int getLottosSize() {
		return this.lottos.size();
	}
}
