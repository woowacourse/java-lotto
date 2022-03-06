package service;

import java.util.ArrayList;
import java.util.List;

import domain.Lotto;
import domain.Lottos;
import domain.OrderForm;

public class LottoService implements LottoStrategy {

	public Lottos createLottos(Lottos manualLottos, OrderForm orderForm) {
		return createManualLottos(manualLottos, createLottos(orderForm.calculateAutoLottoCount(), Lotto.SIZE));
	}

	public Lottos createLottos(int count, int size) {
		List<Lotto> lottos = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			lottos.add(createAutoLotto(size));
		}
		return new Lottos(lottos);
	}

	private Lottos createManualLottos(Lottos manualLottos, Lottos autoLottos) {
		List<Lotto> lottos = new ArrayList<>(manualLottos.getLottos());
		lottos.addAll(autoLottos.getLottos());
		return new Lottos(lottos);
	}
}
