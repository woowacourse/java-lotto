package lotto.domain;

import java.util.Objects;

import static lotto.domain.Money.LOTTO_PRICE;

public class LottoQuantity {
	private final int lottoQuantity;

	public LottoQuantity(int lottoQuantity) {
		validateNonNegativityOf(lottoQuantity);
		this.lottoQuantity = lottoQuantity;
	}

	private void validateNonNegativityOf(int lottoQuantity) {
		if (lottoQuantity < 0) {
			throw new IllegalArgumentException("로또 수량은 0 이상만 가능합니다.");
		}
	}

	public long getTotalPrice() {
		return (long) lottoQuantity * LOTTO_PRICE;
	}

	public int getLeftLottoQuantityOf(int lottoQuantity) {
		return lottoQuantity - this.lottoQuantity;
	}

	public int getLottoQuantity() {
		return lottoQuantity;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		LottoQuantity that = (LottoQuantity) o;
		return lottoQuantity == that.lottoQuantity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(lottoQuantity);
	}
}
