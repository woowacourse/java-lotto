import java.util.ArrayList;
import java.util.List;

import controller.LottoController;

import controller.dto.BuyingInfoDto;
import controller.dto.LottoResultDto;

import service.LottoService;

import view.InputView;
import view.OutputView;

public class Application {

	public static void main(String[] args) {
		LottoController controller = new LottoController(new LottoService());

		String payment = InputView.insertPayment();

		BuyingInfoDto buyingInfoDto = createBuyInfoDto(controller, payment);
		OutputView.printLottoCount(buyingInfoDto);
		OutputView.printLottos(buyingInfoDto.getTotalLottos());

		showLottoResult(controller, buyingInfoDto, payment);
	}

	private static BuyingInfoDto createBuyInfoDto(LottoController controller, String payment) {
		int manualCount = InputView.insertManualLottoCount();
		List<String[]> manualLottos = InputView.createManualLottos(manualCount);
		return controller.buy(payment, manualCount, manualLottos);
	}

	private static void showLottoResult(LottoController controller, BuyingInfoDto buyingInfoDto, String payment) {
		String[] winningLotto = InputView.insertLotto();
		int bonus = InputView.insertBonus();

		LottoResultDto lottoResultDto = controller.showLottoResult(buyingInfoDto.toLotto(), winningLotto, bonus);
		OutputView.printLottoResult(lottoResultDto.getRanks());
		OutputView.printProfitRate(controller.showProfitRate(lottoResultDto.toRank(), payment));
	}
}