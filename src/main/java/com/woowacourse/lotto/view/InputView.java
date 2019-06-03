package com.woowacourse.lotto.view;

import java.util.Scanner;

public class InputView {
	private static final String DEMAND_MONEY_FOR_LOTTO_PURCHASE = "구입금액을 입력해 주세요.";
	private static final String DEMAND_WINNING_LOTTO = "지난 주 당첨 번호를 입력해주세요.";
	private static final String DEMAND_BONUS_BALL = "보너스 볼을 입력해 주세요.";
	private static final String DEMAND_COUNT_OF_MANUALLY_LOTTO = "수동으로 구매할 로또 수를 입력해 주세요.";
	private static final String DEMAND_MANUAL_LOTTO = "수동으로 구매할 번호를 입력해 주세요.";
	private static final String REPLACE_WHITE_SPACE = "\\s";
	private static final Scanner scanner = new Scanner(System.in);

	public static int inputMoneyForPurchaseOfLotto() {
		System.out.println(DEMAND_MONEY_FOR_LOTTO_PURCHASE);
		return Integer.parseInt(scanner.nextLine());
	}

	public static String inputWinningLotto() {
		System.out.println(DEMAND_WINNING_LOTTO);
		return scanner.nextLine().replaceAll(REPLACE_WHITE_SPACE, "");
	}

	public static int inputBonusBall() {
		System.out.println(DEMAND_BONUS_BALL);
		return Integer.parseInt(scanner.nextLine());
	}

	public static int inputCountOfManualLotto() {
		System.out.println(DEMAND_COUNT_OF_MANUALLY_LOTTO);
		return Integer.parseInt(scanner.nextLine());
	}

	public static void printDemandManualLotto() {
		System.out.println(DEMAND_MANUAL_LOTTO);
	}

	public static String inputManualLottoNumber() {
		return scanner.nextLine().replaceAll(REPLACE_WHITE_SPACE, "");
	}
}
