package lotto.view;

import lotto.domain.MoneyForLotto;

/**
 * 클래스 이름 : .java
 *
 * @author
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/20
 */
public class OutputView {
	private static final String ENTER_MONEY_FOR_LOTTO_GUIDE_MESSAGE = "구매금액을 입력해 주세요.";
	private static final String PURCHASED_LOTTO_NUMBER_IS = "%d개를 구매했습니다.\n";

	public static void askEnterMoneyForLotto() {
		System.out.println(ENTER_MONEY_FOR_LOTTO_GUIDE_MESSAGE);
	}

	public static void printExceptionMessage(String exceptionMessage) {
		System.out.println(exceptionMessage);
	}

	public static void printPurchasedLottos(int numberOfLottos) {
		System.out.printf(PURCHASED_LOTTO_NUMBER_IS, numberOfLottos);
	}


}
