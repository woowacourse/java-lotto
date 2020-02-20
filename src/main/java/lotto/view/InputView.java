package lotto.view;

import lotto.domain.MoneyForLotto;

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
	private static Scanner scanner = new Scanner(System.in);

	public static MoneyForLotto getMoneyForLotto() {
		try {
			OutputView.askEnterMoneyForLotto();
			return new MoneyForLotto(Integer.parseInt(scanner.nextLine()));
		} catch (Exception e) {
			OutputView.printExceptionMessage(e.getMessage());
			return getMoneyForLotto();
		}
	}
}
