package lotto.view;

import lotto.domain.CustomLottoCount;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String SEPERATOR = ",";

    public static String promptPrice() {
        System.out.println("구입 금액을 입력해주세요.");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static List<String> promptWinningNumber() {
        System.out.println("지난 주 당첨번호를 입력해 주세요.");
        Scanner scanner = new Scanner(System.in);
        return Arrays.asList(scanner.nextLine().split(SEPERATOR));
    }

    public static String promptBonusBall() {
        System.out.println("보너스 볼을 입력해주세요.");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static String promptCoustomLottoCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static String[] promptCustomLotto(CustomLottoCount customLottoCount) {
        String[] customLottoNumbersInput = new String[customLottoCount.getCustomLottoCount()];
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < customLottoCount.getCustomLottoCount(); i++) {
            customLottoNumbersInput[i] = scanner.nextLine();
        }
        return customLottoNumbersInput;
    }
}
