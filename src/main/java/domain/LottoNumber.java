package domain;

import java.util.Objects;

public class LottoNumber {
	private int lottoNumber;

	public LottoNumber(String lottoNumber) {
		this.lottoNumber = Integer.parseInt(lottoNumber);
		if (this.lottoNumber < 1 || this.lottoNumber > 45) {
			throw new IllegalArgumentException("로또 범위를 벗어난 숫자입니다.");
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		LottoNumber that = (LottoNumber)o;
		return lottoNumber == that.lottoNumber;
	}

	@Override
	public int hashCode() {
		return Objects.hash(lottoNumber);
	}
}
