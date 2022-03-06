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
		return new ManualLottoGenerator(new AutoLottoGenerator(orderForm.calculateAutoLottoCount(), Lotto.SIZE), manualLottos).creatLottos();
	}

	public LottoResult createLottoResult(Lottos lottos, WinningLotto winningLotto) {
		return LottoResult.from(lottos.countRank(winningLotto));
	}
}

