package lotto;

import lotto.dao.TurnDao;
import lotto.domain.*;
import lotto.util.LottoParser;
import lotto.util.RandomNumbersGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class ConsoleApplication {
	private static final int START_COUNT = 0;

	public static void main(String[] args) {
		LottoService service = new LottoService();
		service.charge(InputView.inputBuyMoney());

		int manualPurchaseCount = assignManualPurchaseCount(service);
		int autoPurchaseCount = assignAutoPurchaseCount(service);
		OutputView.showBuyCounts(manualPurchaseCount, autoPurchaseCount);

		OutputView.showLottos(service.getLottos());
		WinningLotto winningLotto = assignWinningLotto();
		GameResult gameResult = service.gameResult();
		gameResult.match(winningLotto);
		OutputView.showGameResult(gameResult);
		deleteInfo(service);
	}

	private static void deleteInfo(final LottoService service) {
		service.deleteAll();
		TurnDao.getInstance().deleteAll();
	}

	private static int assignManualPurchaseCount(final LottoService service) {
		LottoParser parser = new LottoParser();
		int manualPurchaseCount = InputView.inputManualPurchaseCount();
		int retCount = START_COUNT;
		for (; retCount < manualPurchaseCount && service.canBuy(); retCount++) {
			Lotto lotto = parser.parseLotto(InputView.inputManualNumbers());
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
		LottoParser parser = new LottoParser();
		Lotto lotto = parser.parseLotto(InputView.inputWinningLotto());
		LottoNumber bonusNum = parser.parseLottoNumber(InputView.inputBonusLottoNumber());
		try {
			return WinningLotto.of(lotto, bonusNum);
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
			return assignWinningLotto();
		}
	}
}
