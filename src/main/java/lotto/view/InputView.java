package lotto.view;

import java.util.Scanner;

public class InputView {
    private static final String STRING = "구입할 금액을 입력해주세요.";
    private static final String INSERT_MONEY_MESSAGE = STRING;
    private static final String COUNT_OF_CUSTOM_LOTTOS_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String INPUT_CUSTOM_LOTTO_MESSAGE = "수동으로 구매할 번호를 입력해 주세요.";
    private static final String INPUT_WINNING_LOTTO_MESSAGE = "지난주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final String SPLITTER = ",";

    private static final Scanner SCANNER = new Scanner(System.in);

    public static int insertMoney() {
        System.out.println(INSERT_MONEY_MESSAGE);
        return Integer.parseInt(SCANNER.nextLine());
    }

    public static int countOfCustomLottos() {
        System.out.println(COUNT_OF_CUSTOM_LOTTOS_MESSAGE);
        return Integer.parseInt(SCANNER.nextLine());
    }

    public static String[] inputCustomLotto() {
        System.out.println(INPUT_CUSTOM_LOTTO_MESSAGE);
        return inputLotto();
    }

    public static String[] inputWinningLotto() {
        System.out.println(INPUT_WINNING_LOTTO_MESSAGE);
        return inputLotto();
    }

    private static String[] inputLotto() {
        return SCANNER.nextLine().split(SPLITTER);
    }

    public static int inputBonusNumber() {
        System.out.println(INPUT_BONUS_NUMBER_MESSAGE);
        return SCANNER.nextInt();
    }
}
