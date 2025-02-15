package lotto.view;

import java.util.Scanner;

public class InputView implements AutoCloseable {
    private Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public String read() {

        return scanner.nextLine();
    }

    @Override
    public void close() throws Exception {
        this.scanner.close();
    }
}
