package domain;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {
	private static final int MIN_BOUND = 1;
	private static final int MAX_BOUND = 45;
	private int lottoNumber;

	public LottoNumber(String lottoNumber) {
		this.lottoNumber = Integer.parseInt(lottoNumber);
		checkRange(this.lottoNumber);
	}

	public LottoNumber(int lottoNumber) {
		checkRange(lottoNumber);
		this.lottoNumber = lottoNumber;
	}

	private void checkRange(int lottoNumber) {
		if (lottoNumber < MIN_BOUND || lottoNumber > MAX_BOUND) {
			throw new IllegalArgumentException("로또 범위를 벗어난 숫자입니다.");
		}
	}

	public int getLottoNumber() {
		return lottoNumber;
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

	@Override
	public String toString() {
		return String.valueOf(lottoNumber);
	}

	@Override
	public int compareTo(LottoNumber o) {
		return this.lottoNumber - o.lottoNumber;
	}
}
