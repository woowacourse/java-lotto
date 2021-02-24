package lottogame.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static String input() {
        return scanner.nextLine().trim();
    }

    public static String inputMoney() {
        System.out.println("구입 금액을 입력해 주세요.");
        return input();
    }

    public static String inputWinningLottoNumbers() {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
        return input();
    }

    public static String inputBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return input();
    }

    public static String askManualQuantity() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return input();
    }

    public static List<String> askLottoNumbers(int count) {
        List<String> lottoNumbers = new ArrayList<>();
        if (count == 0) return lottoNumbers;
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        for (int i = 0; i < count; i++) {
            lottoNumbers.add(input());
        }
        return lottoNumbers;
    }
}
