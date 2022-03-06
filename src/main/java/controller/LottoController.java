package controller;

import domain.Lottos;
import domain.OrderForm;
import domain.Payment;
import domain.WinningLotto;
import service.LottoService;
import view.InputConvertor;
import view.OutputView;

public class LottoController {

	private final LottoService lottoService;

	public LottoController(LottoService lottoService) {
		this.lottoService = lottoService;
	}

	public void run() {
		Payment payment = InputConvertor.createPayment();

		OrderForm orderForm = InputConvertor.createOrderForm(payment);
		Lottos manualLottos = InputConvertor.createManualLottos(orderForm);
		OutputView.printLottoCount(orderForm);

		Lottos lottos = createLottos(manualLottos, orderForm);
		OutputView.printLottos(lottos);

		WinningLotto winningLotto = InputConvertor.createWinningLotto();

		OutputView.printLottoResult(lottoService.createLottoResult(lottos, winningLotto), payment);
	}

	private Lottos createLottos(Lottos manualLottos, OrderForm orderForm) {
		return lottoService.createLottos(manualLottos, orderForm);
	}
}
