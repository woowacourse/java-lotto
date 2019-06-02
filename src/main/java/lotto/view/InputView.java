package lotto.view;

import java.util.Scanner;

public class InputView {
    private static final String SPLIT_REGEX = ",";
    private static Scanner scanner = new Scanner(System.in);

    public static String InputPrice() {
        System.out.println("구매금액을 입력해 주세요.");
        return scanner.nextLine();
    }

    public static String[] InputWinLottoNumber() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return scanner.nextLine().split(SPLIT_REGEX);
    }

}
