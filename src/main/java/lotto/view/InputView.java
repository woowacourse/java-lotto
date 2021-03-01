package lotto.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static String askMoney() {
        OutputView.printMessage("구입 금액을 입력해 주세요.");
        return scanner.nextLine();
    }

    public static String askManualLottoQuantity() {
        OutputView.printMessage("수동으로 구매할 로또 수를 입력해 주세요.");
        return scanner.nextLine();
    }

    public static List<String> askManualLottoNumbers(int manualQuantity) {
        OutputView.printMessage("수동으로 구매할 번호를 입력해 주세요.");
        List<String> manualNumbers = new ArrayList<>();
        for (int i = 0; i < manualQuantity; i++) {
            manualNumbers.add(scanner.nextLine());
        }
        return manualNumbers;
    }

    public static String askWinningLottoNumbers() {
        OutputView.printMessage("지난 주 당첨 번호를 입력해 주세요.");
        return scanner.nextLine();
    }

    public static String askBonusNumber() {
        OutputView.printMessage("보너스 볼을 입력해 주세요.");
        return scanner.nextLine();
    }
}
