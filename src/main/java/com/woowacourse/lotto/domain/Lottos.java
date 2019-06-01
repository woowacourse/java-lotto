package com.woowacourse.lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Lottos {
	private final List<Lotto> lottos;

	public Lottos(final List<Lotto> lottos) {
		this.lottos = new ArrayList<>(lottos);
	}

	public int size() {
		return lottos.size();
	}

	public Lottos addLottos(Lottos lottos) {
		List<Lotto> addResultLottos = new ArrayList<>(this.lottos);
		addResultLottos.addAll(new ArrayList<>(lottos.getLottos()));
		return new Lottos(addResultLottos);
	}

	public List<Lotto> getLottos() {
		return Collections.unmodifiableList(lottos);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Lottos)) {
			return false;
		}
		final Lottos lottos1 = (Lottos) o;
		return Objects.equals(getLottos(), lottos1.getLottos());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getLottos());
	}
}
