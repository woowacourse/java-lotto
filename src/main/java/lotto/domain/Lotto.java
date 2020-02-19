package lotto.domain;

import java.util.List;

public class Lotto {
	private final List<Integer> lottoNumber;

	public Lotto(final List<Integer> lottoNumber) {
		this.lottoNumber = lottoNumber;
	}

	public boolean contains(int value) {
		return lottoNumber.contains(value);
	}
}
