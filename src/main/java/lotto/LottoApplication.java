package lotto;

import lotto.controller.LottoGame;
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
			LottoGame lottoGame = new LottoGame();
			lottoGame.run();
		} catch (Exception e) {
			OutputView.printError(e.getMessage());
		}
	}
}
