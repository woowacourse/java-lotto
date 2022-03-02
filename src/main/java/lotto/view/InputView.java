package lotto.view;

import java.util.Scanner;

public class InputView {
    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public String getInput() {
        return scanner.nextLine().replaceAll(" ", "");
    }

    public void terminate() {
        scanner.close();
    }
}
