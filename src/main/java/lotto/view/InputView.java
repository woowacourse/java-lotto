package lotto.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import lotto.domain.lotto.LottoNumber;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static String getMoney() {
        System.out.println(InputViewMessages.REQUEST_PURCHASE_MONEY.getMessage());
        return scanner.nextLine();
    }

    public static String[] getLottoLine() {
        System.out.println(InputViewMessages.REQUEST_LAST_WIN_LOTTO_NUMBERS.getMessage());
        String lottoNumbersInput = scanner.nextLine();
        String[] splitLottoNumbersInput = lottoNumbersInput.replace(" ", "").split(",");
        return splitLottoNumbersInput;
    }

    public static int getBonusLottoNumber() {
        System.out.println(InputViewMessages.REQUEST_LAST_WIN_BONUS_BALL.getMessage());
        int number = Integer.parseInt(scanner.nextLine());
        return number;
    }

}