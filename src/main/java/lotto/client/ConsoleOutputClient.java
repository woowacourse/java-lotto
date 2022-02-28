package lotto.client;

public class ConsoleOutputClient implements OutputClient {

    private ConsoleOutputClient() {
    }

    private static class ConsoleOutputClientHelper {
        private static final OutputClient INSTANCE = new ConsoleOutputClient();
    }

    public static OutputClient getInstance() {
        return ConsoleOutputClientHelper.INSTANCE;
    }

    @Override
    public void output(String message) {
        System.out.print(message);
    }
}
