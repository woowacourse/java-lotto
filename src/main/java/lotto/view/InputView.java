package lotto.view;

import lotto.domain.*;
import lotto.util.StringUtils;

import java.util.List;
import java.util.Scanner;

/**
 * 클래스 이름 : .java
 *
 * @author
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/20
 */
public class InputView {
	private static final Scanner SCANNER = new Scanner(System.in);

	public static MoneyForLotto getMoneyForLotto() { // TODO 고려를 해보자
		try {
			OutputView.askEnterMoneyForLotto();
			return new MoneyForLotto(Integer.parseInt(SCANNER.nextLine()));
		} catch (Exception e) { // TODO runtime으로
			OutputView.printExceptionMessage(e);
			return getMoneyForLotto();
		}
	}

	public static WinningLotto getWinningLotto() {
		try {
			OutputView.askEnterWinningLotto();
			List<LottoNumber> winningLottoNumbers = StringUtils.splitIntoLottoNumbers(SCANNER.nextLine());

			return (WinningLotto) LottoFactory.createLottoManual(
					LottoType.WINNING_LOTTO,
					winningLottoNumbers
			);
		} catch (Exception e) {
			OutputView.printExceptionMessage(e);
			return getWinningLotto();
		}
	}

	public static BonusLottoNumber getBonusLottoNumber(WinningLotto winningLotto) {
		try {
			OutputView.askEnterBonusLottoNumber();
			return new BonusLottoNumber(Integer.parseInt(SCANNER.nextLine()), winningLotto);
		} catch (Exception e) {
			OutputView.printExceptionMessage(e);
			return getBonusLottoNumber(winningLotto);
		}
	}
}
