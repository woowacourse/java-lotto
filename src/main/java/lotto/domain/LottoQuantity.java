package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static lotto.domain.Money.LOTTO_PRICE;

public class LottoQuantity {
	public static final String NEGATIVE_LOTTO_QUANTITY_ERROR = "로또 수량은 0 이상만 가능합니다.";

	private final int lottoQuantity;

	public LottoQuantity(int lottoQuantity) {
		validateNonNegativity(lottoQuantity);
		this.lottoQuantity = lottoQuantity;
	}

	private void validateNonNegativity(int lottoQuantity) {
		if (lottoQuantity < 0) {
			throw new IllegalArgumentException(NEGATIVE_LOTTO_QUANTITY_ERROR);
		}
	}

	public Lottos createLottosUsing(LottoGenerator lottoGenerator) {
		List<Lotto> lottos = new ArrayList<>();
		for (int i = 0; i < lottoQuantity; i++) {
			Lotto lotto = lottoGenerator.createLotto();
			lottos.add(lotto);
		}
		return new Lottos(lottos);
	}

	public long getTotalPrice() {
		return (long) lottoQuantity * LOTTO_PRICE;
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
