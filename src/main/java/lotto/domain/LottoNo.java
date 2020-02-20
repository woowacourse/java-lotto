package lotto.domain;

import java.util.Objects;

import lotto.validator.Validator;

public class LottoNo implements Comparable<LottoNo> {
	private final int number;

	public LottoNo(int number) {
		Validator.validateLottoRange(number);
		this.number = number;
	}

	@Override
	public int compareTo(LottoNo other) {
		return this.number - other.number;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		LottoNo lottoNo = (LottoNo)o;
		return number == lottoNo.number;
	}

	@Override
	public int hashCode() {
		return Objects.hash(number);
	}

	@Override
	public String toString() {
		return number + "";
	}
}
