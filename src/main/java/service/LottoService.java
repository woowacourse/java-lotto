package service;

import domain.Lotto;
import domain.LottoResult;
import domain.Lottos;
import domain.OrderForm;

import domain.WinningLotto;
import domain.generator.AutoLottoGenerator;
import domain.generator.ManualLottoGenerator;

public class LottoService {

	public Lottos createLottos(Lottos manualLottos, OrderForm orderForm) {
		Lottos autoLottos = new AutoLottoGenerator().createLottos(orderForm.calculateAutoLottoCount(), Lotto.SIZE);
		return new ManualLottoGenerator().createManualLottos(manualLottos, autoLottos);
	}

	public LottoResult createLottoResult(Lottos lottos, WinningLotto winningLotto) {
		return LottoResult.from(lottos.countRank(winningLotto));
	}
}

