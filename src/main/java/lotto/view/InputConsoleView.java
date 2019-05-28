package lotto.view;

import java.util.Scanner;

public class InputConsoleView implements InputView {
    private static Scanner scanner = new Scanner(System.in);

    @Override
    public long inputMoney() {
        System.out.println("구입 금액을 입력해주세요.");
        return new Scanner(System.in).nextLong();
    }

    @Override
    public int inputCountOfManual() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return new Scanner(System.in).nextInt();
    }

    @Override
    public void printInputManual() {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
    }

    @Override
    public String inputManual() {
        return scanner.nextLine();
    }

    @Override
    public String inputWinningLotto() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return new Scanner(System.in).nextLine();
    }

    @Override
    public int inputBonusNo() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return new Scanner(System.in).nextInt();
    }
}
