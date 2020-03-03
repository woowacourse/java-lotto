package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Lottos {
	private final List<Lotto> lottos;

	public Lottos(List<Lotto> selfLottos, List<Lotto> autoLottos) {
		lottos = new ArrayList<>();
		lottos.addAll(selfLottos);
		lottos.addAll(autoLottos);
	}

	public Stream<Lotto> stream() {
		return lottos.stream();
	}

	public List<Lotto> getLottos() {
		return lottos;
	}
}
