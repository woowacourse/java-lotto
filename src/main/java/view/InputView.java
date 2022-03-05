package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String SCAN_MANUAL_LOTTO_COUNT_GUIDE = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String SCAN_MANUAL_LOTTO_NUMBERS_GUIDE = "수동으로 구매할 번호를 입력해 주세요.";
    private static final String SCAN_MONEY_GUIDE = "구입금액을 입력해 주세요.";
    private static final String SCAN_WINNING_LOTTO_GUIDE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String SCAN_BONUS_NUMBER_GUIDE = "보너스 볼을 입력해 주세요.";
    private static final Scanner SCANNER = new Scanner(System.in);

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

    private static String inputFromUser() {
        return SCANNER.nextLine();
    }

    public static String scanManualLottoCount() {
        System.out.println(SCAN_MANUAL_LOTTO_COUNT_GUIDE);
        return SCANNER.nextLine();
    }

    public static List<String> scanManualLottoNumbers(int manualLottoCount) {
        System.out.println(SCAN_MANUAL_LOTTO_NUMBERS_GUIDE);
        List<String> inputManualLottoNumbers = new ArrayList<>();
        while (manualLottoCount > 0) {
            inputManualLottoNumbers.add(SCANNER.nextLine());
            manualLottoCount--;
        }
        return inputManualLottoNumbers;
    }
}
