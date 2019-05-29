package com.woowacourse.lotto.view;

import java.util.Scanner;

public class InputView {
	private static Scanner scanner = new Scanner(System.in);

	public static int inputMoneyForPurchaseOfLotto() {
		System.out.println(UserOutput.DEMAND_MONEY_FOR_PURCHASHE_LOTTO.getUserOutputMessage());
		return scanner.nextInt();
	}
}
