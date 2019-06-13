package lotto;

import lotto.dao.TurnDao;
import lotto.domain.*;
import lotto.util.LottoParser;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class ConsoleApplication {

	public static void main(String[] args) {
		LottoService service = LottoService.getInstance();
		service.charge(InputView.inputBuyMoney());

		List<Lotto> lottos = assignLottos();
		int manualPurchaseCount = service.assignManualCount(lottos);
		int autoPurchaseCount = service.assignAutoPurchaseCount();
		OutputView.showBuyCounts(manualPurchaseCount, autoPurchaseCount);

		OutputView.showLottos(service.getLottos());
		WinningLotto winningLotto = assignWinningLotto();
		GameResultMatcher gameResultMatcher = service.gameResult();
		gameResultMatcher.match(winningLotto);
		OutputView.showGameResult(gameResultMatcher);
		deleteInfo(service);
	}

	private static List<Lotto> assignLottos() {
		LottoParser parser = new LottoParser();
		List<Lotto> lottos = new ArrayList<>();
		int manualPurchaseCount = InputView.inputManualPurchaseCount();
		for (int i = 0; i < manualPurchaseCount; i++) {
			Lotto lotto = parser.parseLotto(InputView.inputManualNumbers());
			lottos.add(lotto);
		}
		return lottos;
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

	private static void deleteInfo(final LottoService service) {
		service.deleteAll();
		TurnDao.getInstance().deleteAll();
	}
}
