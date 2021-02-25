package lottogame.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static String scan() {
        return scanner.nextLine().trim();
    }

    public static String scanMoney() {
        System.out.println("구입 금액을 입력해 주세요.");
        return scan();
    }

    public static String scanWinningLotto() {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
        return scan();
    }

    public static String scanBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return scan();
    }

    public static String scanManualQuantity() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return scan();
    }

    public static List<String> scanLottos(int count) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<String> lottoNumbers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottoNumbers.add(scan());
        }
        return lottoNumbers;
    }
}
