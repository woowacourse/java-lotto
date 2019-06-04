package lotto.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String LOTTO_NUMBER_DELIMITER = ",";

    public static String inputMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        return SCANNER.nextLine();
    }

    public static String[] inputWinningLotto() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return SCANNER.nextLine().split(LOTTO_NUMBER_DELIMITER);
    }

    public static String inputBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return SCANNER.nextLine().trim();
    }

    public static int inputCountOfManualLotto() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return Integer.parseInt(SCANNER.nextLine());
    }

    public static List<String[]> inputManualLotto(int countOfManualLotto) {
        System.out.println("수동으로 구매할 번호를 입력하세요.");
        List<String[]> manualLottos=new ArrayList<>();
        for (int i = 0; i < countOfManualLotto; i++) {
            manualLottos.add(SCANNER.nextLine().split(LOTTO_NUMBER_DELIMITER));
        }
        return manualLottos;
    }
}
