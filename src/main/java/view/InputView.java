package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InputView {

	private static final Scanner scanner = new Scanner(System.in);
	private static final String NUMBER_PATTERN = "^[0-9]*$";
	private static final Pattern COMPILED_NUMBER_PATTERN = Pattern.compile(NUMBER_PATTERN);
	private static final String WINNING_NUMBER_PATTERN = "^([1-9]{1,2}[,][\\s]?){5}[1-9]{1,2}$";
	private static final Pattern COMPILED_WINNING_NUMBER_PATTERN = Pattern.compile(WINNING_NUMBER_PATTERN);
	private static final String WINNING_NUMBER_DISTRIBUTOR = ",";

	public static int inputValidMoney() {
		final String money = inputMoney();
		return Integer.parseInt(money);
	}

	private static String inputMoney() {
		try {
			System.out.println("구입금액을 입력해 주세요.");
			final String money = scanner.nextLine();
			validateMoney(money);
			return money;
		} catch (IllegalArgumentException exception) {
			System.out.println(exception.getMessage());
			return inputMoney();
		}
	}

	private static void validateMoney(final String money) {
		validateNumber(money);
		validateRange(money);
	}

	private static void validateRange(String money) {
		int number = Integer.parseInt(money);
		if (number < 1000 || number > 100000) {
			throw new IllegalArgumentException("구입 금액의 범위는 1000원~100000원 입니다.");
		}
	}

	private static void validateNumber(String money) {
		if (!COMPILED_NUMBER_PATTERN.matcher(money).matches()) {
			throw new IllegalArgumentException("구입 금액은 숫자여야 합니다.");
		}
	}

	public static List<Integer> inputValidLotteryNumber() {
		final String numbers = inputLotteryNumber();
		return Arrays.stream(numbers.split(WINNING_NUMBER_DISTRIBUTOR))
			.map(String::trim)
			.map(Integer::parseInt)
			.collect(Collectors.toList());
	}

	private static String inputLotteryNumber() {
		try {
			System.out.println("지난 주 당첨 번호를 입력해 주세요.");
			final String winningNumber = scanner.nextLine();
			validateWinningNumber(winningNumber);
			return winningNumber;
		} catch (IllegalArgumentException exception) {
			System.out.println(exception.getMessage());
			return inputLotteryNumber();
		}
	}

	private static void validateWinningNumber(String winningNumber) {
		if (!COMPILED_WINNING_NUMBER_PATTERN.matcher(winningNumber).matches()) {
			throw new IllegalArgumentException("당첨번호는 \"1,2,3,4,5,6\"과 같은 꼴 이어야 합니다");
		}
	}

	public static int inputValidBonusNumber() {
		return Integer.parseInt(inputBonusNumber());
	}

	private static String inputBonusNumber() {
		try {
			System.out.println("보너스 볼을 입력해 주세요.");
			final String bonusNumber = scanner.nextLine();
			validateNumber(bonusNumber);
			return bonusNumber;
		} catch (IllegalArgumentException exception) {
			System.out.println(exception.getMessage());
			return inputBonusNumber();
		}
	}
}
