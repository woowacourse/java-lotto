package controller;

import java.util.List;
import java.util.stream.Collectors;

import domain.Lotto;
import domain.LottoNumber;
import domain.LottoResult;
import domain.Lottos;
import domain.OrderForm;
import domain.Payment;
import domain.WinningLotto;
import service.LottoService;
import view.OutputView;

public class LottoController {

	private final LottoService lottoService;

	public LottoController(LottoService lottoService) {
		this.lottoService = lottoService;
	}

	public Lottos buy(String paymentValue, int countValue, List<String[]> manualLottosValue) {
		Payment payment = createPayment(paymentValue);
		OrderForm orderForm = createOrderForm(payment, countValue);
		Lottos manualLottos = createManualLottos(manualLottosValue);
		OutputView.printLottoCount(orderForm);

		Lottos totalLottos = createLottos(manualLottos, orderForm);
		OutputView.printLottos(totalLottos);
		return totalLottos;
	}

	public LottoResult lookLottoResult(Lottos totalLotto, String[] lotto, int bonus) {
		WinningLotto winningLotto = createWinningLotto(lotto, bonus);
		return lottoService.createLottoResult(totalLotto, winningLotto);
	}

	public Payment createPayment(String payment) {
		return new Payment(payment);
	}

	public OrderForm createOrderForm(Payment payment, int count) {
		return new OrderForm(payment, count);
	}

	public Lottos createManualLottos(List<String[]> lottos) {
		List<Lotto> manualLottos = lottos
			.stream()
			.map(Lotto::of)
			.collect(Collectors.toUnmodifiableList());

		return new Lottos(manualLottos);
	}

	private Lottos createLottos(Lottos manualLottos, OrderForm orderForm) {
		return lottoService.createLottos(manualLottos, orderForm);
	}

	public WinningLotto createWinningLotto(String[] lotto, int bonus) {
		return new WinningLotto(createLotto(lotto), createBonusNumber(bonus));

	}

	public static Lotto createLotto(String[] lotto) {
		return Lotto.of(lotto);
	}

	private static LottoNumber createBonusNumber(int bonusValue) {
		return LottoNumber.of(bonusValue);
	}
}
