package lotto.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import lotto.domain.lotto.LottoNumber;

public class InputView {
    private static final String DELIMITER = ",";
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static String getMoney() {
        System.out.println(InputViewMessages.REQUEST_PURCHASE_MONEY.getMessage());
        return scanner.nextLine();
    }

    public static String[] getSplitLottoNumbers() {
        System.out.println(InputViewMessages.REQUEST_LAST_WIN_LOTTO_NUMBERS.getMessage());
        String lottoNumbersInput = scanner.nextLine();
        String[] splitLottoNumbersInput = lottoNumbersInput.replace(" ", "").split(DELIMITER);
        return splitLottoNumbersInput;
    }

    public static String getBonusLottoNumber() {
        System.out.println(InputViewMessages.REQUEST_LAST_WIN_BONUS_BALL.getMessage());
        return scanner.nextLine();
    }

}