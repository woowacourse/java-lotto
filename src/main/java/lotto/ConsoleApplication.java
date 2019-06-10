package lotto;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.LottosDto;
import lotto.view.OutputView;

import java.util.List;

public class ConsoleApplication {
	private static final int START_COUNT = 0;

	public static void main(String[] args) {
		LottoService service = new LottoService();
		service.charge(InputView.inputBuyMoney());

		int manualPurchaseCount = assignManualPurchaseCount(service);
		int autoPurchaseCount = assignAutoPurchaseCount(service);
		OutputView.showBuyCounts(manualPurchaseCount, autoPurchaseCount);

		OutputView.showLottos(createLottosDto(service.getLottos()));
		WinningLotto winningLotto = assignWinningLotto();
		LottoGameResult gameResult = service.gameResult();
		gameResult.match(winningLotto);
		OutputView.showGameResult(gameResult);
	}

	private static int assignManualPurchaseCount(final LottoService service) {
		InputParser parser = new InputParser();
		int manualPurchaseCount = InputView.inputManualPurchaseCount();
		int retCount = START_COUNT;
		for (; retCount < manualPurchaseCount && service.canBuy(); retCount++) {
			Lotto lotto = parser.makeLotto(InputView.inputManualNumbers());
			service.buy(lotto);
		}
		return retCount;
	}

	private static int assignAutoPurchaseCount(final LottoService service) {
		RandomNumbersGenerator generator = RandomNumbersGenerator.getInstance();
		LottoFactory lottoFactory = new LottoFactory();
		int autoPurchaseCount = START_COUNT;
		for (; service.canBuy(); autoPurchaseCount++) {
			Lotto lotto = lottoFactory.create(generator.generate());
			service.buy(lotto);
		}
		return autoPurchaseCount;
	}

	private static WinningLotto assignWinningLotto() {
		InputParser parser = new InputParser();
		Lotto lotto = parser.makeLotto(InputView.inputWinningLotto());
		LottoNumber bonusNum = parser.makeLottoNumber(InputView.inputBonusLottoNumber());
		try {
			return WinningLotto.of(lotto, bonusNum);
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
			return assignWinningLotto();
		}
	}

	private static LottosDto createLottosDto(final List<Lotto> lottos) {
		DtoConverter converter = new DtoConverter();
		return converter.convertLottosToDto(lottos);
	}
}
