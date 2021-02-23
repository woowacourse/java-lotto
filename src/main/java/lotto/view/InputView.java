package lotto.view;

import lotto.domain.LottoQuantity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static lotto.domain.Money.LOTTO_PRICE;

public class InputView {
	public static final String NON_NUMERIC_ERROR = "숫자만 입력 가능합니다.";
	public static final String NOT_ENOUGH_MONEY_ERROR = String.format("%d원 이상의 금액만 입력 가능합니다.", LOTTO_PRICE);

	private InputView() {
	}

	public static int takeMoneyInput(Scanner scanner) {
		System.out.println("구입금액을 입력해 주세요");
		String moneyInput = scanner.nextLine();
		validateIntegerNumber(moneyInput);
		validateNotEnoughMoney(moneyInput);
		return Integer.parseInt(moneyInput);
	}

	private static void validateNotEnoughMoney(String money) {
		if (Integer.parseInt(money) < LOTTO_PRICE) {
			throw new IllegalArgumentException(NOT_ENOUGH_MONEY_ERROR);
		}
	}

	public static int takeManualLottoQuantityInput(Scanner scanner) {
		System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
		String manualLottoQuantity = scanner.nextLine();
		validateIntegerNumber(manualLottoQuantity);
		System.out.println();
		return Integer.parseInt(manualLottoQuantity);
	}

	public static List<int[]> takeManualLottoNumbersInput(Scanner scanner, LottoQuantity lottoQuantity) {
		System.out.println("수동으로 구매할 번호를 입력해주세요.");
		List<int[]> numbersSequence = new ArrayList<>();
		for (int i = 0; i < lottoQuantity.getLottoQuantity(); i++) {
			String[] numbersInput = scanner.nextLine().split(", ");
			validateIntegerNumbers(numbersInput);
			numbersSequence.add(convertToInts(numbersInput));
		}
		System.out.println();
		return numbersSequence;
	}

	public static List<int[]> takeWinningNumbersInput(Scanner scanner) {
		System.out.println("지난 주 당첨 번호를 입력해 주세요.");
		String[] winningNumberInput = scanner.nextLine().split(", ");
		validateIntegerNumbers(winningNumberInput);
		return convertToNumbersSequence(winningNumberInput);
	}

	private static List<int[]> convertToNumbersSequence(String[] winningNumbersInput) {
		List<int[]> numbersSequence = new ArrayList<>();
		numbersSequence.add(convertToInts(winningNumbersInput));
		return numbersSequence;
	}

	private static int[] convertToInts(String[] winningNumbersInput) {
		return Arrays.stream(winningNumbersInput)
				.mapToInt(Integer::parseInt)
				.toArray();
	}

	private static void validateIntegerNumbers(String[] numbersInput) {
		for (String number : numbersInput) {
			validateIntegerNumber(number);
		}
	}

	public static int takeBonusNumberInput(Scanner scanner) {
		System.out.println("보너스 볼을 입력해 주세요");
		String bonusBallInput = scanner.nextLine();
		validateIntegerNumber(bonusBallInput);
		System.out.println();
		return Integer.parseInt(bonusBallInput);
	}

	private static void validateIntegerNumber(String input) {
		try {
			Integer.parseInt(input);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(NON_NUMERIC_ERROR);
		}
	}
}
