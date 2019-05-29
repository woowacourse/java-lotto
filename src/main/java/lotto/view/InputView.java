package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final String NEW_LINE = System.getProperty("line.separator");
    private static final String SPLITTER = ",";

    private static final Scanner sc = new Scanner(System.in);

    public static int inputBudget() {
        try {
            System.out.println("구입 금액을 입력해주세요.");
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException nfe) {
            System.out.println("정수가 아닙니다. 다시 입력해주세요." + NEW_LINE);
            return inputBudget();
        }
    }

    public static List<Integer> inputWinningLotto() {
        try {
            System.out.println("당첨 번호를 입력해주세요.");
            return Arrays.stream(sc.nextLine().split(SPLITTER)).map(Integer::parseInt).collect(Collectors.toList());
        } catch (NumberFormatException nfe) {
            System.out.println("정수가 아닙니다. 다시 입력해주세요." + NEW_LINE);
            return inputWinningLotto();
        }
    }
}
