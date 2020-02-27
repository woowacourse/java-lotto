package lotto.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
	private static final Scanner scanner = new Scanner(System.in);

	public static String getMoney() {
		System.out.println("구입금액을 입력해주세요.");
		return scanner.nextLine();
	}

	public static String getWinningNumbers() {
		System.out.println("지난 주 당첨 번호를 입력해 주세요.");
		return scanner.nextLine();
	}

	public static String getBonusNumber() {
		System.out.println("보너스 번호를 입력해 주세요.");
		return scanner.nextLine();
	}

	public static String getManualCount() {
		System.out.println("수동으로 구매하려는 로또의 장수를 입력하세요.");
		return scanner.nextLine();
	}

	public static List<String> getManualLottos(int manualLottoCount) {
		List<String> manualInput = new ArrayList<>();
		for (int i = 0; i < manualLottoCount; i++) {
			manualInput.add(scanner.nextLine());
		}
		return manualInput;
	}
}
