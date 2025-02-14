package lotto.view;

import java.util.Scanner;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public int readBuyingAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        return readInteger();
    }

    public String readWinningLotto() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return scanner.nextLine();
    }

    public int readBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return readInteger();
    }

    private int readInteger() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자를 입력해 주세요.");
        }
    }

}
