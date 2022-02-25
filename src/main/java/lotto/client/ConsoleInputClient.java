package lotto.client;

import java.util.Scanner;

public class ConsoleInputClient implements InputClient {

    private final Scanner scanner;
    private final OutputClient outputClient;

    public ConsoleInputClient(Scanner scanner, OutputClient outputClient) {
        this.scanner = scanner;
        this.outputClient = outputClient;
    }

    public String input(String prompt) {
        outputClient.output(prompt);
        return scanner.nextLine();
    }
}
