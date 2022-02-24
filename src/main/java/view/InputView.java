package view;

import java.util.Scanner;

import domain.Lotto;
import domain.LottoNumber;
import domain.Payment;
import domain.WinningLotto;

public class InputView {
	private static final Scanner scanner = new Scanner(System.in);

	private InputView() {}

	public static Payment insertPayment() {
		try {
			OutputView.printGuideMessage("구입금액을 입력해 주세요.");
			return new Payment(scanner.nextLine());
		} catch (Exception e) {
			OutputView.printErrorMessage(e.getMessage());
			return insertPayment();
		}
	}

	public static Lotto insertLotto() {
		try {
			OutputView.printGuideMessage("지난 주 당첨 번호를 입력해 주세요.");
			String inputValue = scanner.nextLine();
			String[] numbers = inputValue.split(",");
			return Lotto.of(numbers);
		} catch (Exception e) {
			OutputView.printErrorMessage(e.getMessage());
			return insertLotto();
		}
	}

	public static WinningLotto insertBonus(Lotto lotto) {
		try {
			OutputView.printGuideMessage("보너스 볼을 입력해 주세요.");
			return new WinningLotto(lotto, new LottoNumber(scanner.nextLine()));
		} catch (Exception e) {
			OutputView.printErrorMessage(e.getMessage());
			return insertBonus(lotto);
		}
	}
}
