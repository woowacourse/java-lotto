package com.woowacourse.lotto.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
	private static Scanner scanner = new Scanner(System.in);

	public static int inputMoneyForPurchaseOfLotto() {
		System.out.println(UserOutput.DEMAND_MONEY_FOR_PURCHASE_LOTTO.getUserOutputMessage());
		return Integer.parseInt(scanner.nextLine());
	}

	public static String inputWinningLotto() {
		System.out.println(UserOutput.DEMAND_WINNING_LOTTO.getUserOutputMessage());
		return scanner.nextLine().replaceAll("\\s", "");
	}

	public static int inputBonusBall() {
		System.out.println(UserOutput.DEMAND_BONUS_BALL.getUserOutputMessage());
		return Integer.parseInt(scanner.nextLine());
	}

	public static int inputCountOfManualLotto() {
		System.out.println(UserOutput.DEMAND_COUNT_OF_MANUALLY_LOTTO.getUserOutputMessage());
		return Integer.parseInt(scanner.nextLine());
	}

	public static List<String> inputManualLottoNumber(int count) {
		System.out.println(UserOutput.DEMAND_MANUAL_LOTTO.getUserOutputMessage());
		List<String> manualLottoNumber = new ArrayList<>();
		while (count -- > 0) {
			manualLottoNumber.add(scanner.nextLine().replaceAll("\\s", ""));
		}
		return manualLottoNumber;
	}
}
