package view;

import java.util.Scanner;

public class InputView {
    Scanner scanner = new Scanner(System.in);

    public String enterPrice() {
        return scanner.nextLine();
    }
}
