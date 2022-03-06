package view;

import java.util.ArrayList;

import controller.LottoController;
import domain.Lotto;

public class InputConvertor {

	private InputConvertor() {
	}

	public static String createPayment(LottoController controller) {
		try {
			String payment = InputView.insertPayment();
			controller.createPayment(payment);
			return payment;
		} catch (Exception e) {
			OutputView.printErrorMessage(e.getMessage());
			return createPayment(controller);
		}
	}

	public static int createOrderForm(LottoController controller, String payment) {
		try {
			int count = InputView.insertManualLottoCount();
			controller.createOrderForm(controller.createPayment(payment), count);
			return count;
		} catch (Exception e) {
			OutputView.printErrorMessage(e.getMessage());
			return createOrderForm(controller, payment);
		}
	}

	public static ArrayList<String[]> createManualLottos(LottoController controller, int count) {
		OutputView.printGuideMessage("수동으로 구매할 번호를 입력해 주세요.");
		ArrayList<String[]> lottos = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			lottos.add(InputView.insertManualLottos());
		}
		try {
			controller.createManualLottos(lottos);
			return lottos;
		} catch (Exception e) {
			OutputView.printErrorMessage(e.getMessage());
			return createManualLottos(controller, count);
		}
	}

	public static String[] createLotto() {
		try {
			String[] winLotto = InputView.insertLotto();
			Lotto.of(winLotto);
			return winLotto;
		} catch (Exception e) {
			OutputView.printErrorMessage(e.getMessage());
			return createLotto();
		}
	}

	public static int createBonusNumber(LottoController controller, String[] winningLotto) {
		try {
			int bonus = InputView.insertBonus();
			controller.createWinningLotto(winningLotto, bonus);
			return bonus;
		} catch (Exception e) {
			OutputView.printErrorMessage(e.getMessage());
			return createBonusNumber(controller, winningLotto);
		}
	}
}
