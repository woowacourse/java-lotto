package lotto.view;

import lotto.domain.Customer;

import java.util.Scanner;

public class InputView {
    private static final String MESSAGE_INPUT_MONEY = "구입금액을 입력해 주세요.";
    private static final String MESSAGE_INPUT_BONUS = "보너스 볼을 입력해 주세요.";
    private static final String MESSAGE_INPUT_WIN_NUMBER = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String MESSAGE_INPUT_MANUAL_LOTTO_COUNT = "수동으로 구매할 로또의 개수를 입력해 주세요.";
    private static final String MESSAGE_INPUT_MANUAL_LOTTO_NUMBER = "수동으로 구매할 로또 번호들을 입력해 주세요.";
    private static final char LINE_SEPARATOR = '\n';

    private static final Scanner scanner = new Scanner(System.in);

    public static String inputMoney() {
        System.out.println(MESSAGE_INPUT_MONEY);
        return scanner.next();
    }

    public static String inputBonusBall() {
        System.out.println(MESSAGE_INPUT_BONUS);
        return scanner.next();
    }

    public static String inputWinNumber() {
        System.out.println(MESSAGE_INPUT_WIN_NUMBER);
        return scanner.next();
    }

    public static String inputManualLottoCount() {
        System.out.println(MESSAGE_INPUT_MANUAL_LOTTO_COUNT);
        return scanner.next();
    }

    public static String inputManualLottoNumber(Customer customer) {
        if (customer.isUserLottoCountOverZero()) {
            System.out.println(MESSAGE_INPUT_MANUAL_LOTTO_NUMBER);
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < customer.getManualLottoCount(); i++) {
            builder.append(scanner.next() + LINE_SEPARATOR);
        }
        return builder.toString();
    }
}
