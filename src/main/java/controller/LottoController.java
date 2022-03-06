package controller;

import domain.Lottos;
import domain.OrderForm;
import domain.Payment;
import domain.WinningLotto;
import service.LottoService;
import service.LottoStrategy;
import view.InputConvertor;
import view.OutputView;

public class LottoController {
	private final LottoStrategy lottoStrategy;
	public LottoController(LottoStrategy lottoStrategy){
		this.lottoStrategy = lottoStrategy;
	}

	public void run() {
		Payment payment = InputConvertor.createPayment();

		OrderForm orderForm = InputConvertor.createOrderForm(payment);
		Lottos manualLottos = InputConvertor.createManualLottos(orderForm);
		OutputView.printLottoCount(orderForm);

		Lottos lottos = createLottos(manualLottos, orderForm);
		OutputView.printLottos(lottos);

		WinningLotto winningLotto = InputConvertor.createWinningLotto();

		OutputView.printLottoResult(lottos.createLottoResult(winningLotto), payment);
	}

	private  Lottos createLottos(Lottos manualLottos, OrderForm orderForm) {
		return lottoStrategy.createLottos(manualLottos, orderForm);
	}
}
