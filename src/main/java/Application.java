import java.util.List;

import controller.LottoController;

import domain.LottoResult;
import domain.Lottos;

import service.LottoService;

import view.InputConvertor;
import view.OutputView;

public class Application {
	public static void main(String[] args) {
		LottoController controller = new LottoController(new LottoService());

		String payment = InputConvertor.createPayment(controller);
		int manualCount = InputConvertor.createOrderForm(controller, payment);
		List<String[]> manualLottos = InputConvertor.createManualLottos(controller, manualCount);

		Lottos totalLottos = controller.buy(payment, manualCount, manualLottos);

		String[] winningLotto = InputConvertor.createLotto();
		int bonus = InputConvertor.createBonusNumber(controller, winningLotto);

		LottoResult lottoResult = controller.lookLottoResult(totalLottos, winningLotto, bonus);
		OutputView.printLottoResult(lottoResult, controller.createPayment(payment));
	}
}