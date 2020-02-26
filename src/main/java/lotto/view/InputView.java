package lotto.view;

import lotto.domain.LottoCount;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    public static final String LOTTO_NUMBERS_DELIMITER = ",";

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() { /* prevent creating InputView instance */ }

    public static String requestLottoMoneyInput() {
        System.out.println("구입금액을 입력해 주세요.");
        return scanner.nextLine();
    }

    public static String requestManualLottoCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return scanner.nextLine();
    }

    public static List<String[]> requestManualLottoInput(LottoCount lottoCount) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<String[]> manualLottos = new ArrayList<>();
        for (int i = 0; i < lottoCount.getManualLottoCountValue(); i++) {
            String[] lotto = scanner.nextLine().split(LOTTO_NUMBERS_DELIMITER);
            manualLottos.add(lotto);
        }
        return manualLottos;
    }

    public static String[] requestWinningLottoInput() {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
        return scanner.nextLine().split(LOTTO_NUMBERS_DELIMITER);
    }

    public static String requestBonusNumberInput() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return scanner.nextLine();
    }
}
