package lotto.view;

import java.util.Scanner;

/**
 * 입력 클래스
 *
 * @version 1.0.0
 * @author K.S.KIM
 * @since 2020/02/19
 */
public class InputView {
	private static final Scanner SCANNER = new Scanner(System.in);

	private InputView() {
	}

	public static long inputLottoMoney() {
		System.out.println("구입금액을 입력해주세요.");
		return Long.parseLong(SCANNER.nextLine());
	}

	public static String inputWinningLotto() {
		System.out.println("지난 주 당첨 번호를 입력해 주세요.");
		return SCANNER.nextLine();
	}

	public static int inputBonusBall() {
		System.out.println("보너스 볼을 입력해 주세요.");
		return Integer.parseInt(SCANNER.nextLine());
	}
}
