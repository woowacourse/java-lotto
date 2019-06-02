package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static String readMoney(String notifyingMessage) {
        System.out.println(notifyingMessage);
        System.out.println("구입금액을 입력해 주세요.");
        return SCANNER.nextLine();
    }

    public static List<String> readWinningLotto(String notifyingMessage) {
        System.out.println(notifyingMessage);
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        List<String> inputs = new ArrayList<>();
        inputs.add(SCANNER.nextLine());
        System.out.println("보너스 볼을 입력해 주세요.");
        inputs.add(SCANNER.nextLine());

        return inputs;
    }
}
