package lotto.client;

import java.util.Scanner;

import lotto.config.ClientConfig;

public class ConsoleInputClient implements InputClient {

    private final Scanner scanner;
    private final OutputClient outputClient;

    private ConsoleInputClient(Scanner scanner, OutputClient outputClient) {
        this.scanner = scanner;
        this.outputClient = outputClient;
    }

    private static class ConsoleInputClientHelper {
        private static final InputClient INSTANCE = new ConsoleInputClient(
            new Scanner(System.in),
            ClientConfig.getOutputClient()
        );
    }

    public static InputClient getInstance() {
        return ConsoleInputClientHelper.INSTANCE;
    }

    public String input(String prompt) {
        outputClient.output(prompt);
        return scanner.nextLine();
    }
}
