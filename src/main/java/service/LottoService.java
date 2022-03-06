package service;

import java.util.ArrayList;
import java.util.List;

import domain.Lotto;
import domain.LottoResult;
import domain.Lottos;
import domain.OrderForm;

import domain.WinningLotto;

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

	public LottoResult createLottoResult(Lottos lottos, WinningLotto winningLotto) {
		return LottoResult.from(lottos.countRank(winningLotto));
	}
}

