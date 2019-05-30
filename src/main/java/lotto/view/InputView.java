package lotto.view;


import java.util.Scanner;

public class InputView {
    private static final Scanner input = new Scanner(System.in);

    public static String inputPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요");
        return input.nextLine();
    }

    public static String inputWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return input.nextLine();
    }
}
