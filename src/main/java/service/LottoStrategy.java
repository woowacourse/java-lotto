package service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import domain.Lotto;
import domain.LottoNumber;
import domain.Lottos;
import domain.OrderForm;

public interface LottoStrategy {
	List<LottoNumber> LOTTO_BUCKET = LottoNumber.ofList();

	default Lotto createAutoLotto(int count) {
		Collections.shuffle(LOTTO_BUCKET);
		return Lotto.from(LOTTO_BUCKET.stream()
			.limit(count)
			.collect(Collectors.toUnmodifiableList()));
	}

	public Lottos createLottos(Lottos manualLottos, OrderForm orderForm);

	public Lottos createLottos(int count, int size);
}
