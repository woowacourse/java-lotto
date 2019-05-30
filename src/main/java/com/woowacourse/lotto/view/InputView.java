package com.woowacourse.lotto.view;

import java.util.Scanner;

public class InputView {
	private static Scanner scanner = new Scanner(System.in);

	public static int inputMoneyForPurchaseOfLotto() {
		System.out.println(UserOutput.DEMAND_MONEY_FOR_PURCHASE_LOTTO.getUserOutputMessage());
		int money = scanner.nextInt();
		scanner.nextLine();
		return money;
	}

	public static String inputWinningLotto() {
		System.out.println(UserOutput.DEMAND_WINNING_LOTTO.getUserOutputMessage());
		return scanner.nextLine();
	}

	public static int inputBonusBall() {
		System.out.println(UserOutput.DEMAND_BONUS_BALL.getUserOutputMessage());
		int bonusBall = scanner.nextInt();
		scanner.nextLine();
		return bonusBall;
	}
}
