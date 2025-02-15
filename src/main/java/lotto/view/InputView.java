package lotto.view;

import java.util.Scanner;

public class InputView implements AutoCloseable {
    private Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public String read(String prompt) {
        System.out.println(prompt);;
        return scanner.nextLine();
    }

    @Override
    public void close() throws Exception {
        this.scanner.close();
    }
}
