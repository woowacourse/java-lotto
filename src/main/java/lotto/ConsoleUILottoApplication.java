package lotto;

import static lotto.view.ConsoleInputView.*;
import static lotto.view.ConsoleOutputView.*;

import java.util.List;
import java.util.Map;

import lotto.controller.LottoController;
import lotto.domain.InvalidLottoMoneyException;
import lotto.domain.Lotto;
import lotto.domain.LottoMoney;
import lotto.domain.LottoNumber;
import lotto.domain.LottoRank;
import lotto.domain.WinningLottoParser;

public class ConsoleUILottoApplication {
	public static void main(String[] args) {
		LottoController lottoController = new LottoController();

		LottoMoney inputLottoMoney = receiveInputMoney();
		int numberOfLotto = inputLottoMoney.getNumberOfLotto();
		printPurchaseCompleteMessage(numberOfLotto);

		List<Lotto> lottos = lottoController.purchaseLotto(numberOfLotto);
		printPurchasedLotto(lottos);

		Lotto winningLotto = new Lotto(WinningLottoParser.parser(inputWinningLottoNumber()));
		LottoNumber bonusLottoNumber = LottoNumber.valueOf(inputBonusLottoNumber());

		Map<LottoRank, Integer> lottoRankCount =
			lottoController.getLottoRankCount(lottos, winningLotto, bonusLottoNumber);
		printStatisticsMessage();
		printStatisticsResult(lottoRankCount);
	}

	private static LottoMoney receiveInputMoney() {
		try {
			return new LottoMoney(inputMoney());
		} catch (InvalidLottoMoneyException ime) {
			printExceptionMessage(ime.getMessage());
			return receiveInputMoney();
		}
	}
}
