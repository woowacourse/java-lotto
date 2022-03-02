package lotto.view.input.reader;

import java.util.Scanner;

public class ConsoleReader implements Reader {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String readLine() {
        return scanner.nextLine();
    }

}
