package com.woowacourse.lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {
	private final List<Lotto> lottos;

	public Lottos(final List<Lotto> lottos) {
		this.lottos = new ArrayList<>(lottos);
	}

	public int getSize() {
		return lottos.size();
	}

	public List<Lotto> getLottos() {
		return Collections.unmodifiableList(lottos);
	}
}
