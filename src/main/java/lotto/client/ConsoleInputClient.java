package lotto.client;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


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
            ConsoleOutputClient.getInstance()
        );
    }

    public static InputClient getInstance() {
        return ConsoleInputClientHelper.INSTANCE;
    }

    @Override
    public String input(String prompt) {
        outputClient.output(prompt);
        return scanner.nextLine();
    }

    @Override
    public List<String> inputRepeat(String prompt, int count) {
        outputClient.output(prompt);
        return IntStream.range(0, count)
            .mapToObj(x -> scanner.nextLine())
            .collect(Collectors.toList());
    }
}
