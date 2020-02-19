package lotto.domain;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Lottos implements Iterable<Lotto>{
	private final List<Lotto> lottos;

	public Lottos(List<Lotto> lottos) {
		this.lottos = Collections.unmodifiableList(lottos);
	}

	public boolean isRightSize(int size) {
		return lottos.size() == size;
	}

	@Override
	public Iterator<Lotto> iterator() {
		return lottos.iterator();
	}

	public int getSize() {
		return lottos.size();
	}
}
