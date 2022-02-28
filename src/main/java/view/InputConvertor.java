package view;

import domain.Lotto;
import domain.LottoNumber;
import domain.Payment;
import domain.WinningLotto;

public class InputConvertor {

	private InputConvertor() {
	}

	public static Payment createPayment() {
		try {
			return new Payment(InputView.insertPayment());
		} catch (Exception e) {
			OutputView.printErrorMessage(e.getMessage());
			return createPayment();
		}
	}

	public static WinningLotto createWinningLotto() {
		return new WinningLotto(createLotto(), createBonusNumber());
	}

	private static Lotto createLotto() {
		try {
			return Lotto.of(InputView.insertLotto());
		} catch (Exception e) {
			OutputView.printErrorMessage(e.getMessage());
			return createLotto();
		}
	}

	private static LottoNumber createBonusNumber() {
		try {
			return LottoNumber.of(InputView.insertBonus());
		} catch (Exception e) {
			OutputView.printErrorMessage(e.getMessage());
			return createBonusNumber();
		}
	}
}
