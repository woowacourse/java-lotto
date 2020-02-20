package lotto;

import lotto.controller.LottoGame;
import lotto.view.InputView;
import lotto.view.OutputView;

/**
 * 로또 게임 실행 클래스
 *
 * @version 1.0.0
 * @author K.S.KIM
 * @since 2020/02/19
 */
public class LottoApplication {
	public static void main(String[] args) {
		try {
			long money = InputView.inputLottoMoney();
			String winningLotto = InputView.inputWinningLotto();
			int bonusNumber = InputView.inputBonusBall();
			LottoGame lottoGame = new LottoGame(money, winningLotto, bonusNumber);
			lottoGame.play();
		} catch (IllegalArgumentException | NullPointerException e) {
			OutputView.printError(e.getMessage());
		}

	}
}
