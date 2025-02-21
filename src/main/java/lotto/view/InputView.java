package lotto.view;

import java.util.Scanner;

public class InputView implements AutoCloseable {
    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public String read() {
        return scanner.nextLine();
    }

    @Override
    public void close() {
        this.scanner.close();
    }
}
