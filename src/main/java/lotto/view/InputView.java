package lotto.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 사용자 입력을 받는다
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/20
 */
public class InputView {
	private static final Scanner SCANNER = new Scanner(System.in);

	public static String getMoneyForLotto() {
		OutputView.askEnterMoneyForLotto();
		return SCANNER.nextLine();
	}

	public static String getManualLottoCount() {
		OutputView.askManualLottoCount();
		return SCANNER.nextLine();
	}

	public static List<String> getManualLottoNumbers(int count) {
		List<String> inputManualLottoNumbers = new ArrayList<>();
		OutputView.askEnterManualLottoNumbers();
		while (count-- > 0) {
			inputManualLottoNumbers.add(SCANNER.nextLine());
		}
		return inputManualLottoNumbers;
	}

	public static String getWinningLotto() {
		OutputView.askEnterWinningLotto();
		return SCANNER.nextLine();
	}

	public static String getBonusLottoNumber() {
		OutputView.askEnterBonusLottoNumber();
		return SCANNER.nextLine();
	}
}
