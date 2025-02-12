package src.view.input;

import java.util.Scanner;

public class ConsoleInputView implements InputView {

    private static final Scanner scanner = new Scanner(System.in);

    @Override
    public int inputPurchaseMoney() {
        try {
            return scanner.nextInt();
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("잘못된 입력입니다.", e);
        }
    }
}
