package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class InputView {
	private static final Scanner scanner = new Scanner(System.in);
	private static final String DELIMITER = ", ";

	public static double inputPurchaseMoney() {
		System.out.println("구입금액을 입력해 주세요.");
		String input = scanner.nextLine();
		validate(input);
		return Double.parseDouble(input);
	}

	public static List<Integer> inputSixNumbers() {
		System.out.println("지난 주 당첨 번호를 입력해 주세요.");
		String input = scanner.nextLine();
		validate(input);
		return Arrays.stream(input.split(DELIMITER))
			.map(Integer::parseInt)
			.collect(Collectors.toList());
	}

	public static int inputBonusNumber() {
		System.out.println("보너스 볼을 입력해 주세요.");
		String input = scanner.nextLine();
		validate(input);
		return Integer.parseInt(input);
	}

	private static void validate(String input) {
		if (input == null || input.isEmpty()) {
			throw new IllegalArgumentException("입력이 없습니다.");
		}
	}

	public static int inputSelfNumberLottoAmount() {
		return Integer.parseInt(scanner.nextLine());
	}
}
