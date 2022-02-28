package view;

import java.util.Scanner;

public class InputView {

    private static final String SCAN_MONEY_GUIDE = "구입금액을 입력해 주세요.";
    private static final String SCAN_WINNING_LOTTO_GUIDE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String SCAN_BONUS_NUMBER_GUIDE = "보너스 볼을 입력해 주세요.";
    private static final Scanner SCANNER = new Scanner(System.in);
    public static final String SCAN_PASSIVE_LOTTO_NUMBERS_COUNT_GUIDE = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String SCAN_PASSIVE_LOTTO_NUMBERS_GUIDE = "수동으로 구매할 번호를 입력해 주세요.";

    public static String scanMoney() {
        System.out.println(SCAN_MONEY_GUIDE);
        return inputFromUser();
    }

    public static String scanWinningLottoNumbers() {
        System.out.println(SCAN_WINNING_LOTTO_GUIDE);
        return inputFromUser();
    }

    public static String scanBonusNumber() {
        System.out.println(SCAN_BONUS_NUMBER_GUIDE);
        return SCANNER.nextLine();
    }

    public static String scanPassiveLottoNumbersCount() {
        System.out.println(SCAN_PASSIVE_LOTTO_NUMBERS_COUNT_GUIDE);
        return SCANNER.nextLine();
    }

    public static String scanPassiveLottoNumbers() {
        System.out.println(SCAN_PASSIVE_LOTTO_NUMBERS_GUIDE);
        return SCANNER.nextLine();
    }

    private static String inputFromUser() {
        return SCANNER.nextLine();
    }
}
