package lotto.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Lottos implements Iterable<Lotto>{
	private final List<Lotto> lottos;

	public Lottos(int numberOfLotto) {
		lottos = new ArrayList<>();
		for (int i = 0; i < numberOfLotto; i++) {
			lottos.add(LottoFactory.create());
		}
	}

	public int getSize() {
		return lottos.size();
	}

	@Override
	public Iterator<Lotto> iterator() {
		return lottos.iterator();
	}
}
