package lotto.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static String inputLottoMoney() {
        System.out.println("금액을 입력");
        return scanner.nextLine();
    }

    public static List<String> inputWinningLotto() {
        List<String> inputs = new ArrayList<>();
        System.out.println("당첨번호");
        inputs.add(scanner.nextLine());
        System.out.println("보너스");
        inputs.add(scanner.nextLine());

        return inputs;
    }
}
