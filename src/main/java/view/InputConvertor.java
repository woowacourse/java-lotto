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
		Lotto winningLotto = createLotto();
		try {
			return new WinningLotto(winningLotto, createBonusNumber(winningLotto));
		} catch (Exception e) {
			return createWinningLotto();
		}
	}

	private static Lotto createLotto() {
		try {
			return Lotto.of(InputView.insertLotto());
		} catch (Exception e) {
			OutputView.printErrorMessage(e.getMessage());
			return createLotto();
		}
	}

	private static LottoNumber createBonusNumber(Lotto winningLotto) {
		try {
			LottoNumber bonus = LottoNumber.of(InputView.insertBonus());
			new WinningLotto(winningLotto, bonus);
			return bonus;
		} catch (Exception e) {
			OutputView.printErrorMessage(e.getMessage());
			return createBonusNumber(winningLotto);
		}
	}
}
