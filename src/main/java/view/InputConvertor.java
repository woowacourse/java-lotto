package view;

import java.util.List;
import java.util.stream.Collectors;

import domain.Lotto;
import domain.LottoNumber;
import domain.Lottos;
import domain.OrderForm;
import domain.Payment;
import domain.WinningLotto;

public class InputConvertor {

	public static final String DELIMITER_COMMA = ",";

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

	public static OrderForm createOrderForm(Payment payment) {
		try {
			return new OrderForm(payment, InputView.insertManualLottoCount());
		} catch (Exception e) {
			OutputView.printErrorMessage(e.getMessage());
			return createOrderForm(payment);
		}
	}

	public static Lottos createManualLottos(OrderForm orderForm) {
		try {
			List<Lotto> lottos = InputView.insertManualLottos(orderForm)
				.stream()
				.map(lotto -> Lotto.of(lotto.split(DELIMITER_COMMA)))
				.collect(Collectors.toUnmodifiableList());

			return new Lottos(lottos);
		} catch (Exception e) {
			OutputView.printErrorMessage(e.getMessage());
			return createManualLottos(orderForm);
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
