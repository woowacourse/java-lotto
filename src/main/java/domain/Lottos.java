package domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

	private List<Lotto> lottos = new ArrayList<>();

	public Lottos(int price, LottoNumbersGenerator lottoNumbersGenerator) {
		int count = price / 1000;
		while (count-- > 0) {
			this.lottos.add(new Lotto(lottoNumbersGenerator));
		}
	}

	public List<Lotto> getLottos() {
		return this.lottos;
	}

	public int getLottosSize() {
		return this.lottos.size();
	}
}
