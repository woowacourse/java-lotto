package lotto.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import lotto.domain.lotto.LottoNumber;

public class InputView {
    private static final String DELIMITER = ",";
    private static final String REQUEST_PURCHASE_MONEY = "구입금액을 입력해주세요.";
    private static final String REQUEST_LAST_WIN_LOTTO_NUMBERS = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String REQUEST_LAST_WIN_BONUS_BALL = "보너스 볼을 입력해 주세요.";

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static String getMoney() {
        System.out.println(REQUEST_PURCHASE_MONEY);
        return scanner.nextLine();
    }

    public static String[] getSplitLottoNumbers() {
        System.out.println(REQUEST_LAST_WIN_LOTTO_NUMBERS);
        String lottoNumbersInput = scanner.nextLine();
        String[] splitLottoNumbersInput = lottoNumbersInput.replace(" ", "").split(DELIMITER);
        return splitLottoNumbersInput;
    }

    public static String getBonusLottoNumber() {
        System.out.println(REQUEST_LAST_WIN_BONUS_BALL);
        return scanner.nextLine();
    }

}