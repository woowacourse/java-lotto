package lotto.view;

import lotto.domain.*;
import lotto.util.StringUtils;

import java.util.List;
import java.util.Scanner;

/**
 * 클래스 이름 : InputView.java
 *
 * @author 히히, 토니
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/20
 */
public class InputView {
	private static final Scanner SCANNER = new Scanner(System.in);

	public static int getMoneyForLotto() {
		OutputView.askEnterMoneyForLotto();
		try {
            return Integer.parseInt(SCANNER.nextLine());
        } catch (NumberFormatException e) {
		    throw new WrongMoneyForLottoException("금액은 정수여야 합니다.");
        }
	}

	public static List<Integer> getWinningLotto() {
		OutputView.askEnterWinningLotto();
		return StringUtils.splitIntoIntList((SCANNER.nextLine()));
	}

	public static int getBonusLottoNumber() {
		OutputView.askEnterBonusLottoNumber();
		return Integer.parseInt(SCANNER.nextLine());
	}
}
