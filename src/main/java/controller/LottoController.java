package controller;

import domain.LottoResult;
import domain.Lottos;
import domain.OrderForm;
import domain.Payment;
import domain.WinningLotto;
import service.LottoMachine;
import view.InputConvertor;
import view.OutputView;

public class LottoController {

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

	private static Lottos createLottos(Lottos manualLottos, OrderForm orderForm) {
		return LottoMachine
			.createManualAndAutoMixLottos(manualLottos, orderForm.calculateAutoLottoCount());
	}
}
