package lotto.domain;

import java.util.List;

public class Lotto {
	private final List<Integer> lottoNumber;

	public Lotto(final List<Integer> lottoNumber) {
		validate(lottoNumber);
		this.lottoNumber = lottoNumber;
	}

	private void validate(List<Integer> lottoNumber) {
		validateNullOrEmpty(lottoNumber);
	}

	private void validateNullOrEmpty(List<Integer> lottoNumber) {
		if (lottoNumber == null || lottoNumber.isEmpty()) {
			throw new IllegalArgumentException("null이나 빈 값은 들어올 수 없습니다!");
		}
	}

	public boolean contains(int value) {
		return lottoNumber.contains(value);
	}
}
