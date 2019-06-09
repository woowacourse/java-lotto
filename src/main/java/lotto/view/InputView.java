package lotto.view;

import lotto.domain.LottoCount;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String nextLine = "\n";

    public static String inputMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        return scanner.nextLine();
    }

    public static String inputManualLottoCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return scanner.nextLine();
    }

    public static String inputManualLotto(LottoCount lottoCount) {
        StringBuilder sb = new StringBuilder();
        if (lottoCount.isZero()) {
            return sb.toString();
        }
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        for (int i = 0; i < lottoCount.getCount(); i++) {
            sb.append(scanner.nextLine()).append(nextLine);
        }
        return sb.toString();
    }

    public static String inputWinningLotto() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return scanner.nextLine();
    }

    public static String inputBonusNo() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return scanner.nextLine();
    }
}
