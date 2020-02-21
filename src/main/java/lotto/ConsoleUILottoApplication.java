package lotto;

import static lotto.domain.LottoStore.*;
import static lotto.domain.WinningLottoParser.*;
import static lotto.view.ConsoleInputView.*;
import static lotto.view.ConsoleOutputView.*;

import java.util.List;
import java.util.Map;

import lotto.domain.InvalidLottoMoneyException;
import lotto.domain.Lotto;
import lotto.domain.LottoMoney;
import lotto.domain.LottoNumber;
import lotto.domain.LottoRank;
import lotto.domain.WinningLotto;

public class ConsoleUILottoApplication {
	public static void main(String[] args) {
		LottoMoney inputLottoMoney = receiveInputMoney();
		int numberOfLotto = inputLottoMoney.getNumberOfLotto();
		printPurchaseCompleteMessage(numberOfLotto);

		List<Lotto> lottos = purchaseLotto(numberOfLotto);
		printPurchasedLotto(lottos);

		Lotto winningLottoNumber = new Lotto(parseToLottoNumberList(inputWinningLottoNumber()));
		LottoNumber bonusLottoNumber = LottoNumber.valueOf(inputBonusLottoNumber());
		printStatisticsMessage();

		WinningLotto winningLotto = new WinningLotto(winningLottoNumber, bonusLottoNumber);
		Map<LottoRank, Integer> winningLottoCount = winningLotto.getWinningLottoCount(lottos);
		printWinningResult(winningLottoCount);

		int winningRatio = winningLotto.getWinningRatio(winningLottoCount, inputLottoMoney);
		printWinningRatio(winningRatio);
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
