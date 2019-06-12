package com.woowacourse.lotto.view;

import java.util.Scanner;

import com.woowacourse.lotto.domain.LottoMoney;
import com.woowacourse.lotto.domain.WinningLotto;
import com.woowacourse.lotto.exception.InvalidMoneyException;
import com.woowacourse.lotto.exception.InvalidNumberException;
import com.woowacourse.lotto.utils.StringSeparator;

public class InputView {
	private static final String DEMAND_MONEY_FOR_LOTTO_PURCHASE = "구입금액을 입력해 주세요.";
	private static final String DEMAND_WINNING_LOTTO = "지난 주 당첨 번호를 입력해주세요.";
	private static final String DEMAND_BONUS_BALL = "보너스 볼을 입력해 주세요.";
	private static final String DEMAND_COUNT_OF_MANUALLY_LOTTO = "수동으로 구매할 로또 수를 입력해 주세요.";
	private static final String DEMAND_MANUAL_LOTTO = "수동으로 구매할 번호를 입력해 주세요.";
	private static final String REPLACE_WHITE_SPACE = "\\s";
	private static final Scanner SCANNER = new Scanner(System.in);

	public static LottoMoney inputMoneyForPurchaseOfLotto() {
		System.out.println(DEMAND_MONEY_FOR_LOTTO_PURCHASE);
		try {
			return new LottoMoney(Integer.parseInt(SCANNER.nextLine()));
		} catch (InvalidMoneyException e) {
			System.out.println(e.getMessage());
			return inputMoneyForPurchaseOfLotto();
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
			return inputMoneyForPurchaseOfLotto();
		}
	}

	public static WinningLotto inputWinningLotto() {
		System.out.println(DEMAND_WINNING_LOTTO);
		String winningLotto = SCANNER.nextLine().replaceAll(REPLACE_WHITE_SPACE, "");
		try {
			return new WinningLotto(StringSeparator.splitString(winningLotto), inputBonusBall());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return inputWinningLotto();
		} catch (InvalidNumberException e) {
			System.out.println(e.getMessage());
			return inputWinningLotto();
		}
	}

	private static int inputBonusBall() {
		System.out.println(DEMAND_BONUS_BALL);
		try {
			return Integer.parseInt(SCANNER.nextLine());
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
			return inputBonusBall();
		}
	}

	public static int inputCountOfManualLotto() {
		System.out.println(DEMAND_COUNT_OF_MANUALLY_LOTTO);
		try {
			return Integer.parseInt(SCANNER.nextLine());
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
			return inputCountOfManualLotto();
		}
	}

	public static void printDemandManualLotto() {
		System.out.println(DEMAND_MANUAL_LOTTO);
	}

	public static String inputManualLottoNumber() {
		return SCANNER.nextLine().replaceAll(REPLACE_WHITE_SPACE, "");
	}
}
