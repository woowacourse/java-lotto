package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static String readMoney(String notifyingMessage) {
        System.out.printf(notifyingMessage + "\n");
        System.out.println("구입금액을 입력해 주세요.");
        return readLine();
    }

    public static List<String> readWinningLotto(String notifyingMessage) {
        System.out.printf(notifyingMessage + "\n");
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        List<String> inputs = new ArrayList<>();
        inputs.add(readLine());
        System.out.println("보너스 볼을 입력해 주세요.");
        inputs.add(readLine());

        return inputs;
    }

    public static String readNumNonRandomLottos(String notifyingMessage) {
        System.out.printf(notifyingMessage + "\n");
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");

        return readLine();
    }

    public static void readNonRandomLottos() {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
    }

    public static String readNonRandomLotto(String notifyingMessage) {
        System.out.printf(notifyingMessage + "\n");
        return readLine();
    }

    private static String readLine() {
        return SCANNER.nextLine();
    }
}
