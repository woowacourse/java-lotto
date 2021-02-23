package lotto.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static String getMoneyInput() {
        System.out.println("구입금액을 입력해 주세요.");
        return SCANNER.nextLine();
    }

    public static String getWinningNumbersInput() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return SCANNER.nextLine();
    }

    public static String getBonusBallInput() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return SCANNER.nextLine();
    }

    public static String getManuallyBuyAmountInput() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return SCANNER.nextLine();
    }

    public static List<String> getManualLottoTicketsInput(int manuallyBuyCount) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");

        List<String> lottoTicketsInput = new ArrayList<>();
        for (int i = 0; i < manuallyBuyCount; i++) {
            lottoTicketsInput.add(SCANNER.nextLine());
        }

        return lottoTicketsInput;
    }
}
