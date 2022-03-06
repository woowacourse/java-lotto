package old.client;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ConsoleInputClient implements InputClient {

    private static final Scanner scanner = new Scanner(System.in);
    private final OutputClient outputClient;

    public ConsoleInputClient(OutputClient outputClient) {
        this.outputClient = outputClient;
    }

    private static class ConsoleInputClientHelper {
        private static final InputClient INSTANCE = new ConsoleInputClient(
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
