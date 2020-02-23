package lotto.view;

import lotto.domain.*;
import lotto.util.StringUtils;

import java.util.List;
import java.util.Scanner;

/**
 * 클래스 이름 : InputView.java
 *
 * @author 히히, 토니
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/20
 */
public class InputView {
	private static final Scanner SCANNER = new Scanner(System.in);

	public static int getMoneyForLotto() { // TODO 고려를 해보자
		OutputView.askEnterMoneyForLotto();
		return Integer.parseInt(SCANNER.nextLine());
	}
/*
	public static WinningLotto getWinningLotto() { // Todo: 로또번호 말고 int 리스트로!!
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
*/

	public static List<Integer> getWinningLotto() {
		OutputView.askEnterWinningLotto();
		return StringUtils.splitIntoIntList((SCANNER.nextLine()));
	}

	public static int getBonusLottoNumber() {
		OutputView.askEnterBonusLottoNumber();
		return Integer.parseInt(SCANNER.nextLine());
	}
}
