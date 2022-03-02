package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import domain.OrderForm;

public class InputView {
	private static final Scanner scanner = new Scanner(System.in);

	private InputView() {
	}

	public static String insertPayment() {
		OutputView.printGuideMessage("구입금액을 입력해 주세요.");
		return scanner.nextLine();
	}

	public static int insertManualLottoCount() {
		OutputView.printGuideMessage("수동으로 구매할 로또 수를 입력해 주세요.");
		try {
			return Integer.parseInt(scanner.nextLine());
		} catch (NumberFormatException e) {
			OutputView.printErrorMessage(e.getMessage());
			return insertManualLottoCount();
		}
	}

	public static List<String> insertManualLottos(OrderForm orderForm) {
		List<String> manualLottos = new ArrayList<>();
		OutputView.printGuideMessage("수동으로 구매할 번호를 입력해 주세요.");

		while (!orderForm.isEqualQuantity(manualLottos.size())) {
			manualLottos.add(scanner.nextLine());
		}
		return manualLottos;
	}

	public static String[] insertLotto() {
		try {
			OutputView.printGuideMessage("지난 주 당첨 번호를 입력해 주세요.");
			String inputValue = scanner.nextLine();
			return inputValue.split(",");
		} catch (Exception e) {
			OutputView.printErrorMessage(e.getMessage());
			return insertLotto();
		}
	}

	public static int insertBonus() {
		try {
			OutputView.printGuideMessage("보너스 볼을 입력해 주세요.");
			return Integer.parseInt(scanner.nextLine());
		} catch (Exception e) {
			OutputView.printErrorMessage(e.getMessage());
			return insertBonus();
		}
	}
}
