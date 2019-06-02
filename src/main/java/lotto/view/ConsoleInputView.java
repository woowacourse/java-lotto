package lotto.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleInputView {
    public static int inputMoney() {
        System.out.println("구입 금액을 입력해주세요.");
        return new Scanner(System.in).nextInt();
    }

    public static String inputRewardTicket() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return new Scanner(System.in).nextLine();
    }

    public static int inputBonusBall() {
        System.out.println("보너스 볼을 입력하세요.");
        return new Scanner(System.in).nextInt();
    }

    public static int inputNumberOfManual() {
        System.out.println("수동으로 구매할 갯수를 입력하세요");
        return new Scanner(System.in).nextInt();
    }

    public static List<String> intputManualLottoNumbers(int number) {
        if (number != 0) {
            System.out.println("수동으로 구매할 번호를 입력하세요");
        }
        List<String> inputs = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            inputs.add(new Scanner(System.in).nextLine());
        }
        return inputs;
    }
}
