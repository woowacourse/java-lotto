package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Lottos implements Iterable<Lotto> {
	List<Lotto> lottos;

	public Lottos(List<Lotto> lottos) {
		this.lottos = new ArrayList<>(lottos);
	}

	@Override
	public Iterator<Lotto> iterator() {
		return lottos.iterator();
	}
}
