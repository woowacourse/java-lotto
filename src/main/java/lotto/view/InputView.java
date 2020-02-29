package lotto.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
	private static final Scanner scanner = new Scanner(System.in);

	public static String inputPayment() {
		System.out.println("구입금액을 입력해주세요");
		return scanner.nextLine();
	}

	public static String inputWinningNumbers() {
		System.out.println("지난주 당첨 번호를 입력해주세요");
		return scanner.nextLine();
	}

	public static String inputBonusNumber() {
		System.out.println("보너스 볼을 입력해 주세요");
		return scanner.nextLine();
	}

	public static String inputManualLottoCount() {
		System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
		return scanner.nextLine();
	}

	public static List<String> inputManualNumbers(int count) {
		List<String> result = new ArrayList<>();
		System.out.println("수동으로 구매할 번호를 입력해 주세요.");
		while (count-- > 0) {
			result.add(scanner.nextLine());
		}
		return result;
	}
}
